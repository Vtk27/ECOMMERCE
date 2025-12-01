package clover.orderservice.rest;

import clover.orderservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "http://localhost:8082")
public interface ProductClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id);
    @PutMapping("/products/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Product p);
}
