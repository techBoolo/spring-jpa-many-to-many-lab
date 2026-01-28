package com.example.spring_jpa_many_to_many_lab.student.dto;

import com.example.spring_jpa_many_to_many_lab.course.dto.NestedCourseResponseDto;

import java.util.Set;

public record StudentResponseDto(
        Long id,
        String name,
        Set<NestedCourseResponseDto> courses
) {
}
