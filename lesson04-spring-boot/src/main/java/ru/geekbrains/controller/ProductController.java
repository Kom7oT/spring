package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.naming.Binding;
import javax.validation.Valid;

@RequestMapping("/products")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    //Добавление товара
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new Product("", null));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult binding) {
        if (binding.hasErrors()){
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/products";
    }

    //Удаление товара
    @GetMapping("/delete")
    public String delete(@RequestParam long id, Model model) {
        productRepository.delete(id);
        listPage(model);
        return "products";
    }
}
