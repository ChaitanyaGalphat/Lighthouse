package com.lighthouse.learning.dto;

public class SlideDTO {
    private Long slideId;
    private String title;
    private Long lectureId;

    public SlideDTO(Long slideId, String title, Long lectureId) {
        this.slideId = slideId;
        this.title = title;
        this.lectureId = lectureId;
    }

    public Long getSlideId() {
        return slideId;
    }

    public void setSlideId(Long slideId) {
        this.slideId = slideId;
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

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }
}
