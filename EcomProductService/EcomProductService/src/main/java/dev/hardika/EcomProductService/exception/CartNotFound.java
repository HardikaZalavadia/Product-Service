package dev.hardika.EcomProductService.exception;

public class CartNotFound extends RuntimeException{
    public CartNotFound() {
    }

    public CartNotFound(String message) {
        super(message);
    }
}
