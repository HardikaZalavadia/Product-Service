package dev.hardika.EcomProductService.exception;

public class ProductIdInvalidException extends RuntimeException{
    public ProductIdInvalidException() {
    }

    public ProductIdInvalidException(String message) {
        super(message);
    }

}
