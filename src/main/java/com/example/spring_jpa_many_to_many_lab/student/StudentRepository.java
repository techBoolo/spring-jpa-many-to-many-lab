package com.example.spring_jpa_many_to_many_lab.student;

import com.example.spring_jpa_many_to_many_lab.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
