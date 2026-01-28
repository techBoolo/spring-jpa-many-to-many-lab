package com.example.spring_jpa_many_to_many_lab.course;

import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
