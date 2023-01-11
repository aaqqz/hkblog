package com.hkblog.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class HkBlogException extends RuntimeException{

    public final Map<String, String> validation = new HashMap<>();

    public HkBlogException(String message) {
        super(message);
    }

    public HkBlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
