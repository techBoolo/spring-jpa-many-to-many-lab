package com.example.spring_jpa_many_to_many_lab.student.dto;

import java.util.Set;

public record StudentUpdateDto(
        String name,
        Set<Long> courseIds
) {
}
