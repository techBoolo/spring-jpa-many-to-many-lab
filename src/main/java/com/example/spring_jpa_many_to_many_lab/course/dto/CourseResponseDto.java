package com.example.spring_jpa_many_to_many_lab.course.dto;

import com.example.spring_jpa_many_to_many_lab.student.dto.NestedStudentResponseDto;

import java.util.Set;

public record CourseResponseDto(
        Long id,
        String name,
        Set<NestedStudentResponseDto> students
) {
}
