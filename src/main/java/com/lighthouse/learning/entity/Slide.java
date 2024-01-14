package com.lighthouse.learning.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Slide")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slide_id;

    @NotBlank(message = "Title is required ")
    @Column(length = 255)
    private String title;

    @Column(name = "lecture_id")
    private Long lectureId;

    public Slide() {
    }

    public Slide(String title) {
        this.title = title;
    }

    // Getters and Setters
    public Long getSlideId() {
        return slide_id;
    }

    public void setSlideId(Long slideId) {
        this.slide_id = slideId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLecture(Long lectureId) {
        this.lectureId = lectureId;
    }
}