package com.lighthouse.learning.dto;

import java.util.List;

public class TeacherDTO {
    private Long teacherId;
    private String name;
    private String education;
    private Long salary;
    private List<Long> lectureIds;;

    public TeacherDTO(Long teacherId, String name, String education, Long salary, List<Long> lectureIds) {
        this.teacherId = teacherId;
        this.name = name;
        this.education = education;
        this.salary = salary;
        this.lectureIds = lectureIds;
    }
    public TeacherDTO()
    {}
    public String getName() {
        return name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public List<Long> getLectureIds() {
        return lectureIds;
    }

    public void setLectureIds(List<Long> lectureIds) {
        this.lectureIds = lectureIds;
    }
}
