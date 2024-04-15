package dev.hardika.EcomProductService.exception;

public class NoProductsPresentException extends RuntimeException{
    public NoProductsPresentException() {
    }

    public NoProductsPresentException(String message) {
        super(message);
    }
}
