package com.example.spring_jpa_many_to_many_lab.course;

import com.example.spring_jpa_many_to_many_lab.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

}
