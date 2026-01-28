package com.example.spring_jpa_many_to_many_lab.course;

import com.example.spring_jpa_many_to_many_lab.course.dto.CourseRequestDto;
import com.example.spring_jpa_many_to_many_lab.course.dto.CourseResponseDto;
import com.example.spring_jpa_many_to_many_lab.course.dto.CourseUpdateDto;
import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public CourseResponseDto createCourse(@RequestBody CourseRequestDto courseRequestDto){
        return courseService.createCourse(courseRequestDto);
    }

    @GetMapping
    public List<CourseResponseDto> getCourses(){
        return courseService.getCourses();
    }

    @GetMapping("/{id}")
    public CourseResponseDto getCourse(@PathVariable Long id){
        return courseService.getCourse(id);
    }

    @PutMapping("/{id}")
    public CourseResponseDto updateCourse(
            @PathVariable Long id,
            @RequestBody CourseUpdateDto courseUpdateDto){
        return courseService.updateCourse(id, courseUpdateDto);
    }
}
