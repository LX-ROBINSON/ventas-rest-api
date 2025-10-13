package org.merariway.saleswarehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ErrorGeneral> handleErrorConvert(HttpMessageConversionException ex) {
        ErrorGeneral err = new ErrorGeneral("Formato JSON incorrecto | El contenido que enviaste no existe", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
