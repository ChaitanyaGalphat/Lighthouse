package com.lighthouse.learning.dto;

import com.lighthouse.learning.entity.Lecture;

import java.util.List;
import java.util.Set;

public class TeacherResponseDTO {

    private Long teacherId;
    private String name;
    private String education;
    private Long salary;
    private Set<LectureDTO> lectures;;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Set<LectureDTO> getLectureIds() {
        return lectures;
    }

    public void setLectures(Set<LectureDTO> lectureIds) {
        this.lectures = lectureIds;
    }
}
