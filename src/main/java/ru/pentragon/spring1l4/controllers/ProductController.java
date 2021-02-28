package ru.pentragon.spring1l4.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pentragon.spring1l4.model.Product;
import ru.pentragon.spring1l4.services.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("product")
public class ProductController {
    public ProductService productService;

    public ProductController( ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("frontProduct", productService.getAllRecords());
        return "all_products";
    }

    @GetMapping("/{id}")///product/{id}
    public String getProductByID(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.getByID(id);
        System.out.println(product.isPresent());
        if(!product.isPresent()){
            return "product_not_found";
        }
        model.addAttribute("frontProduct", product.get());
        return "product_info";
    }

    @PostMapping("/add")
//    public String addNewBox(@RequestParam Long id, @RequestParam String color, @RequestParam int size) {
    public String addNewBox(@ModelAttribute Product product) {
        productService.add(product);
        return "redirect:/product/all";
    }

    @PostMapping("/json/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewJsonBox(@RequestBody Product product) {
        productService.add(product);
    }

    @GetMapping("/remove/{id}")
    public String deleteBoxById(@PathVariable Long id) {
        productService.deleteRecordByID(id);
        return "redirect:/product/all";
    }

}
