package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/products")
@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        model.addAttribute("products", productService.findAll(pageValue, sizeValue));
        return "products";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_form";
    }

    //Добавление товара
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @PostMapping
    public String save(@ModelAttribute("product") ProductDto product, BindingResult binding) {
        if (binding.hasErrors()) {
            return "product_form";
        } else
            productService.save(product);
        return "redirect:/products";
    }

    //Удаление товара
    @GetMapping("/delete")
    public String delete(@RequestParam long id, Model model) {
        productService.deleteById(id);
        return "products";
    }
}
