package com.example.spring_jpa_many_to_many_lab.student.entity;

import com.example.spring_jpa_many_to_many_lab.course.entity.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();
    // default set to empty Set, otherwise the code(this.courses.addAll(courses)) in the service
    // will throw an error of trying to add list in null value initially(i.e null value)

    public void addCourses(Collection<Course> courses) {
        if (courses != null) {
            this.courses.addAll(courses);
            for (Course course : courses) {
                course.getStudents().add(this);
            }
        }
    }

}
