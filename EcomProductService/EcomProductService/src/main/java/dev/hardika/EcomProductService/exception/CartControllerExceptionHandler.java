package dev.hardika.EcomProductService.exception;

import dev.hardika.EcomProductService.controller.CartController;
import dev.hardika.EcomProductService.dto.ExceptionHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = CartController.class)
public class CartControllerExceptionHandler {
    @ExceptionHandler(CartNotFound.class)
    public ResponseEntity handleCartNotFoundException(CartNotFound ce){
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(ce.getMessage(), 404);
        return new ResponseEntity<>(exceptionHandlerDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartNotFoundOfThisId.class)
    public ResponseEntity handleCartNotFoundOfThisId(CartNotFoundOfThisId ce){
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(ce.getMessage(), 404);
        return new ResponseEntity<>(exceptionHandlerDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RandomException.class)
    public ResponseEntity handleCarRamdomException(RandomException re){
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(re.getMessage(), 404);
        return new ResponseEntity<>(exceptionHandlerDTO, HttpStatus.NOT_FOUND);
    }
}
