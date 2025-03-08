package com.anota.ai.catalogmanager.category.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotDeletableException extends RuntimeException {
    public CategoryNotDeletableException(String message) {
        super(message);
    }
}
