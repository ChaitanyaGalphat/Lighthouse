package com.lighthouse.learning.controller;

import com.lighthouse.learning.entity.Lecture;
import com.lighthouse.learning.exception.LectureNotFoundException;
import com.lighthouse.learning.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    @Autowired
    LectureService lectureService;

    @GetMapping
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = lectureService.getAllLectures();
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLectureById(@PathVariable Long id) {
        try {
            Lecture lecture = lectureService.getLectureById(id);
            return ResponseEntity.status(HttpStatus.OK).body(lecture);
        } catch (LectureNotFoundException ex) {
            String errorMessage = "Lecture with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<?> createLecture(@RequestBody Lecture lecture) {
        try {
            Lecture savedLecture = lectureService.saveLecture(lecture);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLecture);
        } catch (IllegalArgumentException exception) {
            // Handle validation errors with a 400 BAD_REQUEST status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide the Title");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLecture(@PathVariable Long id, @RequestBody Lecture updatedLecture) {
        try {
            Lecture updated = lectureService.updateLecture(id, updatedLecture);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } catch (LectureNotFoundException ex) {
            String errorMessage = "Lecture with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (IllegalArgumentException exception) {
            // Handle validation errors with a 400 BAD_REQUEST status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide the Title");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLecture(@PathVariable Long id) {
        try {
            lectureService.deleteLecture(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (LectureNotFoundException ex) {
            String errorMessage = "Lecture with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping("/{lectureId}/teachers/{teacherId}")
    public ResponseEntity<Lecture> addTeacherToLecture(@PathVariable Long lectureId, @PathVariable Long teacherId) {

        Lecture lecture = lectureService.addTeacherToLecture(lectureId, teacherId);
        return ResponseEntity.ok(lecture);
    }
}