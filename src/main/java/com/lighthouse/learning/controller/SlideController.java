package com.lighthouse.learning.controller;

import com.lighthouse.learning.dto.SlideDTO;
import com.lighthouse.learning.entity.Slide;
import com.lighthouse.learning.exception.LectureNotFoundException;
import com.lighthouse.learning.exception.SlideNotFoundException;
import com.lighthouse.learning.service.SlideService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private SlideService slideService;

    public SlideController(SlideService slideService) {
        this.slideService = slideService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSlide(@PathVariable Integer id){
        try {
            SlideDTO slide = slideService.getSlideDetails(id);
            System.out.print(slide);
            return ResponseEntity.status(HttpStatus.OK).body(slide);
        } catch (LectureNotFoundException ex) {
            String errorMessage = "Lecture with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<?> createSlide(@Valid @RequestBody Slide slide) {
        try {
            Slide createdSlide = slideService.createSlide(slide);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSlide);
        } catch (LectureNotFoundException ex) {
            String errorMessage = "Lecture with ID " + slide.getLectureId() + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSlide(@PathVariable Integer id, @RequestBody Slide updatedSlide) {
        try {
            Slide updated = slideService.updateSlide(id, updatedSlide);
            return ResponseEntity.ok(updated);
        } catch (SlideNotFoundException ex) {
            String errorMessage = "Slide with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable Integer id) {
        try {
            slideService.deleteSlide(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (SlideNotFoundException ex) {
            String errorMessage = "Slide with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}