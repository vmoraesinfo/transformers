package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Model.BeautyError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(IllegalArgumentException .class)
    public @ResponseBody
    ResponseEntity HanleIlegalArgumentException(HttpServletRequest request, Exception ex){
        return ResponseEntity.notFound().eTag(ex.getMessage()).build();
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentNotValidException ex, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();

        List<String> messages = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(fieldError -> {
            messages.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        BeautyError beautyError =
                new BeautyError(HttpStatus.BAD_REQUEST, messages);
        return new ResponseEntity<Object>(
                beautyError, new HttpHeaders(), beautyError.getStatus());
    }
}
