package com.example.spring_jpa_many_to_many_lab.student.mapper;

import com.example.spring_jpa_many_to_many_lab.student.dto.StudentRequestDto;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentResponseDto;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentUpdateDto;
import com.example.spring_jpa_many_to_many_lab.student.entity.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student toEntity(StudentRequestDto studentRequestDto);

    StudentResponseDto toDto(Student student);

    List<StudentResponseDto> toDtoList(List<Student> students);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(StudentUpdateDto studentUpdateDto, @MappingTarget Student student);
}
