package com.lighthouse.learning.service;

import com.lighthouse.learning.Repository.LectureRepository;
import com.lighthouse.learning.Repository.SlideRepository;
import com.lighthouse.learning.dto.SlideDTO;
import com.lighthouse.learning.entity.Lecture;
import com.lighthouse.learning.entity.Slide;
import com.lighthouse.learning.exception.LectureNotFoundException;
import com.lighthouse.learning.exception.SlideNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlideService {

    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private LectureRepository lectureRepository;


    public Slide createSlide(Slide slide) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(slide.getLectureId());
        if (optionalLecture.isPresent()) {
            return  slideRepository.save(slide);
        } else {
            throw new LectureNotFoundException(slide.getLectureId());
        }
    }

    public Slide updateSlide(Integer slideId, Slide updatedSlide) {
        Slide existingSlide = slideRepository.findById(slideId)
                .orElseThrow(() -> new SlideNotFoundException(slideId));

        existingSlide.setTitle(updatedSlide.getTitle());
        return slideRepository.save(existingSlide);
    }

    public void deleteSlide(Integer slideId) {
        Slide existingSlide = slideRepository.findById(slideId)
                .orElseThrow(() -> new SlideNotFoundException(slideId));
        slideRepository.delete(existingSlide);
    }

    public SlideDTO getSlideDetails(Integer slideId) {
        Optional<Slide> optionalSlide = slideRepository.findById(slideId);

        if (optionalSlide.isPresent()) {
            Slide slide =  optionalSlide.get();
            SlideDTO slideDTO = new SlideDTO(slide.getSlideId(), slide.getTitle(), slide.getLectureId());
            return slideDTO;
        } else {
            throw new SlideNotFoundException(slideId);
        }
    }
}
