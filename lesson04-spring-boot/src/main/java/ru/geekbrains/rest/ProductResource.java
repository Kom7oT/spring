package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.service.ProductService;

import java.sql.SQLException;
import java.util.Optional;

@RequestMapping("/rest/v1/product")
@RestController
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    private Page<ProductDto> findAll(@RequestParam Optional<Integer> page,
                                     @RequestParam Optional<Integer> size,
                                     @RequestParam Optional<String> sortField) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        return productService.findAll(
                pageValue,
                sizeValue,
                sortFieldValue);
    }

    @GetMapping("/{id}/id")
    public Optional<ProductDto> findById(@PathVariable long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto product) {
        if (product.getId() != null) {
            throw new ProductErrorResponse();
        }
        return productService.save(product);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto user) {
        if (user.getId() == null) {
            throw new ProductErrorResponse();
        }
        return productService.save(user);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc) {
        ProductErrorResponse productErrorResponse = new
                ProductErrorResponse();
        productErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        productErrorResponse.setMessage(exc.getMessage());
        productErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse,
                HttpStatus.NOT_FOUND);
    }
}
