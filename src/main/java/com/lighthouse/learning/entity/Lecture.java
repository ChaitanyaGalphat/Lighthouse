package com.lighthouse.learning.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lecture_id;

    @NotBlank(message = "Title is required ")
    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer duration;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Lectures_Teachers",
            joinColumns = {@JoinColumn(name = "lecture_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
    )
    private Set<Teacher> teachers = new HashSet<>();

    public Integer getLectureId() {
        return lecture_id;
    }

    public void setLectureId(Integer lectureId) {
        this.lecture_id = lectureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}