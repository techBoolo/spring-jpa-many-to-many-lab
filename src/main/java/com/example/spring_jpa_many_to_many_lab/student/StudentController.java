package com.example.spring_jpa_many_to_many_lab.student;

import com.example.spring_jpa_many_to_many_lab.student.dto.StudentRequestDto;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.createStudent(studentRequestDto);
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }
}
