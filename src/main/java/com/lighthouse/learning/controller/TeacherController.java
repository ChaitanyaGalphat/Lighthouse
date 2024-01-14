package com.lighthouse.learning.controller;

import com.lighthouse.learning.dto.LectureDTO;
import com.lighthouse.learning.dto.TeacherDTO;
import com.lighthouse.learning.dto.TeacherRequestDTO;
import com.lighthouse.learning.dto.TeacherResponseDTO;
import com.lighthouse.learning.entity.Lecture;
import com.lighthouse.learning.entity.Teacher;
import com.lighthouse.learning.exception.TeacherNotFoundException;
import com.lighthouse.learning.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            return ResponseEntity.status(HttpStatus.OK).body(teacher);
        } catch (TeacherNotFoundException ex) {
            String errorMessage = "Teacher with ID " + teacherId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


//    @PostMapping
//    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
//        Teacher createdTeacher = teacherService.createTeacher(teacher);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
//    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher updatedTeacher) {
        try {
            Teacher teacher = teacherService.updateTeacher(teacherId, updatedTeacher);
            return ResponseEntity.status(HttpStatus.OK).body(teacher);
        } catch (TeacherNotFoundException ex) {
            String errorMessage = "Teacher with ID " + teacherId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long teacherId) {
        try {
            teacherService.deleteTeacher(teacherId);
            return ResponseEntity.noContent().build();
        } catch (TeacherNotFoundException ex) {
            String errorMessage = "Teacher with ID " + teacherId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacherWithLectures(@RequestBody TeacherDTO teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setEducation(teacherDto.getEducation());
        teacher.setSalary(teacherDto.getSalary());

        Teacher savedTeacher = teacherService.addTeacherWithLectures(teacher, teacherDto.getLectureIds());
        return ResponseEntity.ok(savedTeacher);
    }

}
