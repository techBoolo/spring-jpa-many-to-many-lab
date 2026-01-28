package com.example.spring_jpa_many_to_many_lab.course;

import com.example.spring_jpa_many_to_many_lab.course.dto.CourseRequestDto;
import com.example.spring_jpa_many_to_many_lab.course.dto.CourseResponseDto;
import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public CourseResponseDto createCourse(@RequestBody CourseRequestDto courseRequestDto){
        return courseService.createCourse(courseRequestDto);
    }
}
