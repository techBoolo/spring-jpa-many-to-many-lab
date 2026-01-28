package com.example.spring_jpa_many_to_many_lab.course.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Set;

public record CourseRequestDto(
        String name,

        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        Set<Long> studentIds
) {
}
