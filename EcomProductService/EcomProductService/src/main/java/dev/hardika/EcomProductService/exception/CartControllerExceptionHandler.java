package dev.hardika.EcomProductService.exception;

import dev.hardika.EcomProductService.dto.ExceptionHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartControllerExceptionHandler {
    @ExceptionHandler(CartNotFound.class)
    public ResponseEntity handleCartNotFoundException(CartNotFound ce){
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(ce.getMessage(), 404);
        return new ResponseEntity<>(exceptionHandlerDTO, HttpStatus.NOT_FOUND);
    }
}
