package com.lighthouse.learning.dto;

import java.util.List;

public class TeacherRequestDTO {
    private String name;
    private String education;
    private Long salary;
    private List<Long> lectures;

    // Constructors, getters, and setters

    public TeacherRequestDTO() {
    }

    public TeacherRequestDTO(String name, String education, Long salary, List<Long> lectures) {
        this.name = name;
        this.education = education;
        this.salary = salary;
        this.lectures = lectures;
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

    public List<Long> getLectures() {
        return lectures;
    }

    public void setLectures(List<Long> lectures) {
        this.lectures = lectures;
    }
}
