package dev.hardika.EcomProductService.exception.clientException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {
        super(s);
    }

    public UserNotFoundException() {
    }
}
