package com.example.spring_jpa_many_to_many_lab.course.mapper;


import com.example.spring_jpa_many_to_many_lab.course.dto.CourseRequestDto;
import com.example.spring_jpa_many_to_many_lab.course.dto.CourseUpdateDto;
import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import com.example.spring_jpa_many_to_many_lab.course.dto.CourseResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    Course toEntity(CourseRequestDto courseRequestDto);

    CourseResponseDto toDto(Course course);

    List<CourseRequestDto> toDtoList(List<Course> courses);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CourseUpdateDto courseUpdateDto, @MappingTarget Course course);
}
