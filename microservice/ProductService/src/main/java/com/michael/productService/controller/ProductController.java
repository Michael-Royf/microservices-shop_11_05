package com.michael.productService.controller;

import com.michael.productService.payload.request.ProductRequest;
import com.michael.productService.payload.response.MessageResponse;
import com.michael.productService.payload.response.ProductResponse;
import com.michael.productService.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), CREATED);
    }


    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), OK);
    }

    @GetMapping("/productId/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId) {
        return new ResponseEntity<>(productService.getProductById(productId), OK);
    }


    @GetMapping("/productName/{productName}")
    public ResponseEntity<ProductResponse> getProductByName(@PathVariable("productName") String productName) {
        return new ResponseEntity<>(productService.getProductByProductName(productName), OK);
    }


    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("productId") Long productId) {
        return new ResponseEntity<>(productService.deleteProduct(productId), OK);
    }

    @PutMapping("/reduceQuantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("productId") Long productId,
                                               @RequestParam("quantity") Long quantity) {
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(OK);

    }

}
