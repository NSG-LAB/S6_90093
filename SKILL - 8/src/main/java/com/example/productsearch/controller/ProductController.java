package com.example.productsearch.controller;

import com.example.productsearch.entity.Product;
import com.example.productsearch.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "derived") String mode) {
        if ("jpql".equalsIgnoreCase(mode)) {
            return productRepository.findProductsByCategoryJpql(category);
        }
        return productRepository.findByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> filterByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> getProductsSortedByPrice() {
        return productRepository.findAllSortedByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> getProductsAbovePrice(@PathVariable double price) {
        return productRepository.findProductsAbovePrice(price);
    }
}
