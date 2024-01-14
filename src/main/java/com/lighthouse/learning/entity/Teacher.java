package com.lighthouse.learning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @NotBlank(message = "Name is required")
    @Column(length = 255)
    private String name;

    @NotBlank(message = "Education is required")
    @Column(length = 255)
    private String education;

    @Positive(message = "Salary must be a positive number")
    @Column(name = "salary")
    private Long salary;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "teachers",cascade = {CascadeType.ALL})
    private Set<Lecture> lectures = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

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

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
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
}