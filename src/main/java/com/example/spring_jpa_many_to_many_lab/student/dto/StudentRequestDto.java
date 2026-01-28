package com.example.spring_jpa_many_to_many_lab.student.dto;

import java.util.Set;

public record StudentRequestDto(
        String name,
        Set<Long> courseIds
) {
}
