
package com.example.order_service;

import org.springframework.web.client.RestClient;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RestClient restClient;

    public OrderController() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @GetMapping
    public List<Map<String, Object>> getOrders() {
        return List.of(
            Map.of("id", 1, "productId", 1, "quantity", 2),
            Map.of("id", 2, "productId", 2, "quantity", 1)
        );
    }

    @GetMapping("/products")
    public String getProductsFromService() {
        return restClient.get()
                .uri("/api/products")
                .retrieve()
                .body(String.class);
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable int id) {
        return restClient.get()
                .uri("/api/products/{id}", id)
                .retrieve()
                .body(String.class);
    }
}
