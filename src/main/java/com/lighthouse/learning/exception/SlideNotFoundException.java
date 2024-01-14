package com.lighthouse.learning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SlideNotFoundException extends RuntimeException {
    public SlideNotFoundException(Integer slideId) {
        super("Lecture with ID " + slideId + " not found");
    }
}
