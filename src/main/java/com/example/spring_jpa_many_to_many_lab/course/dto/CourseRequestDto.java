package com.example.spring_jpa_many_to_many_lab.course.dto;

import java.util.Set;

public record CourseRequestDto(
        String name,
        Set<Long> studentIds
) {
}
