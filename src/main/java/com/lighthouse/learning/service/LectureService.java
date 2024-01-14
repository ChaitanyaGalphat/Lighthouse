package com.lighthouse.learning.service;

import com.lighthouse.learning.Repository.LectureRepository;
import com.lighthouse.learning.Repository.TeacherRepository;
import com.lighthouse.learning.entity.Lecture;
import com.lighthouse.learning.entity.Teacher;
import com.lighthouse.learning.exception.LectureNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Lecture getLectureById(Long id) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isPresent()) {
            return optionalLecture.get();
        } else {
            throw new LectureNotFoundException(id);
        }
    }

    public Lecture saveLecture(Lecture lecture) {
        if (lecture.getTitle() == null || lecture.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Lecture title cannot be empty");
        }
        return lectureRepository.save(lecture);
    }

    public Lecture updateLecture(Long id, Lecture updatedLecture) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isPresent()) {
            if (updatedLecture.getTitle() == null || updatedLecture.getTitle().isEmpty()) {
                throw new IllegalArgumentException("Lecture title cannot be empty");
            }
            Lecture existingLecture = optionalLecture.get();
            existingLecture.setTitle(updatedLecture.getTitle());
            existingLecture.setDescription(updatedLecture.getDescription());
            existingLecture.setDuration(updatedLecture.getDuration());
            return lectureRepository.save(existingLecture);
        }
        else {
            throw new LectureNotFoundException(id);
        }
    }

    public void deleteLecture(Long id) {
        Optional<Lecture> existingLectureOptional = lectureRepository.findById(id);

        if (existingLectureOptional.isPresent()) {
            Lecture existingLecture = existingLectureOptional.get();
            lectureRepository.delete(existingLecture);
        } else {
            throw new LectureNotFoundException(id);
        }
    }

////----------------

    public Lecture addTeacherToLecture(Long lectureId, Long teacherId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        lecture.getTeachers().add(teacher);
        return lectureRepository.save(lecture);
    }

    public Lecture removeTeacherFromLecture(Long lectureId, Long teacherId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        lecture.getTeachers().remove(teacher);
        return lectureRepository.save(lecture);
    }

}
