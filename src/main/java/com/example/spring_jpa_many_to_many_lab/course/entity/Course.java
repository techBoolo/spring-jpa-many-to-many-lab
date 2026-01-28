package com.example.spring_jpa_many_to_many_lab.course.entity;

import com.example.spring_jpa_many_to_many_lab.student.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SoftDelete
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "courses")
    @SoftDelete
    private Set<Student> students;

    public void preRemove(){
        new HashSet<>(this.students).forEach(student -> {
            student.removeCourse(this);
        });
    }
}
