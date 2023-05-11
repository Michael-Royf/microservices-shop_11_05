package com.michael.productService.exceptions.payload;

public class ProductNotFoundExceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ProductNotFoundExceptions(String message) {
        super(message);
    }
}
