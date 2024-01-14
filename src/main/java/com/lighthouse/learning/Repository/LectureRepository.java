package com.lighthouse.learning.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lighthouse.learning.entity.Lecture;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
