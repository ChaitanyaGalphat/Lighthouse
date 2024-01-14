package com.lighthouse.learning.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LectureNotFoundException extends RuntimeException{
    public LectureNotFoundException(Long id) {
        super("Lecture with ID " + id + " not found");
    }
}
