package com.example.spring_jpa_many_to_many_lab.course;

import com.example.spring_jpa_many_to_many_lab.course.dto.CourseRequestDto;
import com.example.spring_jpa_many_to_many_lab.course.dto.CourseResponseDto;
import com.example.spring_jpa_many_to_many_lab.course.dto.CourseUpdateDto;
import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import com.example.spring_jpa_many_to_many_lab.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Course course = courseMapper.toEntity(courseRequestDto);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDto(savedCourse);
    }

    public List<CourseResponseDto> getCourses(){
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDtoList(courses);
    }

    public CourseResponseDto getCourse(Long id){
        Course course = courseRepository.findById(id).orElseThrow();
        return courseMapper.toDto(course);
    }

    public CourseResponseDto updateCourse(Long id, CourseUpdateDto courseUpdateDto){
        Course course = courseRepository.findById(id).orElseThrow();
        courseMapper.updateEntityFromDto(courseUpdateDto, course);
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.toDto(updatedCourse);
    }

    public void deleteCourse(Long id){
        Course course = courseRepository.findById(id).orElseThrow();

        course.preRemove();
        courseRepository.delete(course);
    }

}
