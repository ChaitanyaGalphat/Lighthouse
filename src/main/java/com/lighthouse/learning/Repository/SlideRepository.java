package com.lighthouse.learning.Repository;

import com.lighthouse.learning.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Integer> {
}