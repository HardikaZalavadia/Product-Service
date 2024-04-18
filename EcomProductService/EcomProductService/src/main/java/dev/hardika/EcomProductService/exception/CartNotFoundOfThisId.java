package dev.hardika.EcomProductService.exception;

public class CartNotFoundOfThisId extends RuntimeException {
    public CartNotFoundOfThisId() {
    }

    public CartNotFoundOfThisId(String message) {
        super(message);
    }
}
