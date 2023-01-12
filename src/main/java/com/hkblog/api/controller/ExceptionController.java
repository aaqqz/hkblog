package com.hkblog.api.controller;

import com.hkblog.api.exception.HkBlogException;
import com.hkblog.api.exception.InvalidRequest;
import com.hkblog.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidRequestHandler(MethodArgumentNotValidException e) {

        ErrorResponse body = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(new HashMap<>())
                .build();

        for (FieldError FieldError : e.getFieldErrors()) {
            System.out.println("FieldError = " + FieldError);
            body.addValidation(FieldError.getField(), FieldError.getDefaultMessage());
        }

        ResponseEntity<ErrorResponse> response = ResponseEntity.status(400)
                .body(body);

        return response;
    }

    @ResponseBody
    @ExceptionHandler(HkBlogException.class)
    public ResponseEntity<ErrorResponse> hkBlogException(HkBlogException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);
        return response;
    }
}
