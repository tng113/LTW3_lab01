package com.example.productservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Dùng chung một danh sách cho cả 2 API
    private static final List<Map<String, Object>> PRODUCTS = List.of(
        Map.of("id", 1, "name", "Mouse", "price", 150000),
        Map.of("id", 2, "name", "Keyboard", "price", 250000),
        Map.of("id", 3, "name", "Monitor", "price", 2500000)
    );

    @GetMapping
    public List<Map<String, Object>> getProducts() {
        return PRODUCTS;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable int id) {
        return PRODUCTS.stream()
                .filter(p -> p.get("id").equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}