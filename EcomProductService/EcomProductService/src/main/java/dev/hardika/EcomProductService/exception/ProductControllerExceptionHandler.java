package dev.hardika.EcomProductService.exception;

import dev.hardika.EcomProductService.dto.ExceptionHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerExceptionHandler {
    @ExceptionHandler({ProductNotFoundException.class, NoProductsPresentException.class})
    public ResponseEntity handleNoProductException(ProductNotFoundException pe){
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(pe.getMessage(),404);
        return new ResponseEntity<>(exceptionHandlerDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({ProductIdInvalidException.class})
    public ResponseEntity handleProductIdInvalidException(ProductIdInvalidException pe){
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(pe.getMessage(),400);
        return new ResponseEntity<>(exceptionHandlerDTO,HttpStatus.BAD_REQUEST);
    }

}
