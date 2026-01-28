package com.example.spring_jpa_many_to_many_lab.student;

import com.example.spring_jpa_many_to_many_lab.course.CourseRepository;
import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentRequestDto;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentResponseDto;
import com.example.spring_jpa_many_to_many_lab.student.dto.StudentUpdateDto;
import com.example.spring_jpa_many_to_many_lab.student.entity.Student;
import com.example.spring_jpa_many_to_many_lab.student.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public StudentResponseDto updateStudent(Long id, StudentUpdateDto studentUpdateDto){
        Student student = studentRepository.findById(id).orElseThrow();
        studentMapper.updateEntityFromDto(studentUpdateDto, student);

        if(studentUpdateDto.courseIds() != null){
            List<Course> courses = courseRepository.findAllById(studentUpdateDto.courseIds());
            student.setCourses(new HashSet<>(courses));
        }

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Transactional
    public StudentResponseDto partialStudentUpdate(Long id, StudentUpdateDto studentUpdateDto){
        Student student = studentRepository.findById(id).orElseThrow();
        studentMapper.updateEntityFromDto(studentUpdateDto, student);

        if(studentUpdateDto.removeCourseIds() != null){
            List<Course> courses = courseRepository.findAllById(studentUpdateDto.removeCourseIds());
            student.removeCourses(courses);
        }
        if(studentUpdateDto.addCourseIds() != null){
            List<Course> courses = courseRepository.findAllById(studentUpdateDto.addCourseIds());
            student.addCourses(courses);
        }

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Transactional
    public void deleteStudent(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
    }
}
