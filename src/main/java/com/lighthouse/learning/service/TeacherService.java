package com.lighthouse.learning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.lighthouse.learning.Repository.LectureRepository;
import com.lighthouse.learning.Repository.TeacherRepository;
import com.lighthouse.learning.dto.LectureDTO;
import com.lighthouse.learning.dto.TeacherDTO;
import com.lighthouse.learning.dto.TeacherRequestDTO;
import com.lighthouse.learning.entity.Lecture;
import com.lighthouse.learning.exception.TeacherNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lighthouse.learning.entity.Teacher;

@Service

public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LectureRepository lectureRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isPresent()) {
            return optionalTeacher.get();
        } else {
            throw new TeacherNotFoundException(teacherId);
        }
    }
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long teacherId, Teacher updatedTeacher) {
        Optional<Teacher> existingTeacherOptional = teacherRepository.findById(teacherId);
        if (existingTeacherOptional.isPresent()) {
            Teacher existingTeacher = existingTeacherOptional.get();
            existingTeacher.setName(updatedTeacher.getName());
            existingTeacher.setEducation(updatedTeacher.getEducation());
            existingTeacher.setSalary(updatedTeacher.getSalary());
            return teacherRepository.save(existingTeacher);
        } else {
            throw new TeacherNotFoundException(teacherId);
        }

    }

    public void deleteTeacher(Long teacherId) {
        Optional<Teacher> existingTeacherOptional = teacherRepository.findById(teacherId);
        if (existingTeacherOptional.isPresent()) {
            Teacher existingTeacher = existingTeacherOptional.get();
            teacherRepository.delete(existingTeacher);
        } else {
            throw new TeacherNotFoundException(teacherId);
        }
    }

    public Teacher addTeacherWithLectures(Teacher teacher, List<Long> lectureIds) {
        Set<Lecture> lectures = lectureIds.stream()
                .map(id -> lectureRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecture not found")))
                .collect(Collectors.toSet());

        teacher.setLectures(lectures);
        return teacherRepository.save(teacher);
    }


}