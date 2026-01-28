package com.example.spring_jpa_many_to_many_lab.student;

import com.example.spring_jpa_many_to_many_lab.course.CourseRepository;
import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentRequestDto;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentResponseDto;
import com.example.spring_jpa_many_to_many_lab.student.entity.Student;
import com.example.spring_jpa_many_to_many_lab.student.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        Student student = studentMapper.toEntity(studentRequestDto);

        if(studentRequestDto.courseIds() != null) {
            // we must check the provided ids since the findAllById will throw an error if ids are null
            List<Course> courses = courseRepository.findAllById(studentRequestDto.courseIds());
            student.addCourses(courses);
        }

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    public List<StudentResponseDto> getStudents(){
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDtoList(students);
    }

    public StudentResponseDto getStudent(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        return studentMapper.toDto(student);
    }

}
