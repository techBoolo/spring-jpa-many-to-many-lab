# Study Guide: JPA Many-to-Many & Soft Delete

This guide provides a detailed explanation of the implementation techniques used in the **Spring JPA Many-to-Many Lab**.

## 1. Bidirectional Many-to-Many Relationship

### Owning Side vs. Inverse Side
In a bidirectional relationship, JPA requires one side to be the "owner".
- **Owning Side (`Student`)**: Defines the join table using `@JoinTable`. Changes to the relationship are tracked through this side.
- **Inverse Side (`Course`)**: Uses the `mappedBy` attribute to point back to the owning field.

```java
// Student.java (Owning Side)
@ManyToMany
@JoinTable(
    name = "student_courses",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private Set<Course> courses = new HashSet<>();

// Course.java (Inverse Side)
@ManyToMany(mappedBy = "courses")
private Set<Student> students;
```

### Synchronizing Both Sides
To maintain data integrity in memory, you must update both sides of the relationship when adding or removing elements.

```java
// Inside Student.java
public void addCourse(Course course) {
    this.courses.add(course);
    course.getStudents().add(this);
}

public void removeCourse(Course course) {
    this.courses.remove(course);
    course.getStudents().remove(this);
}
```

## 2. Soft Delete Implementation

The project uses Hibernate's `@SoftDelete` annotation, introduced in Hibernate 6.4+.

### How it Works:
1. **Annotation**: Place `@SoftDelete` on the entity class.
2. **Behavior**: 
   - `repository.delete(entity)` triggers an `UPDATE` statement that sets a `deleted` flag (default) instead of a `DELETE` statement.
   - `repository.findAll()` automatically appends a `WHERE deleted = false` clause to queries.

### Cascading Soft Deletes
In a many-to-many relationship, soft-deleting one side does not automatically remove the association in the join table unless handled.
- In `Course.java`, the `preRemove()` method is used to manually detach students before the course is soft-deleted.

```java
public void preRemove(){
    new HashSet<>(this.students).forEach(student -> {
        student.removeCourse(this);
    });
}
```

## 3. MapStruct DTO Mapping

MapStruct is used to automate the conversion between Entities and DTOs.

### Key Features Used:
- **`@Mapper(componentModel = "spring")`**: Integrates the mapper as a Spring Bean.
- **Update Mapping**: The `updateEntityFromDto` method allow updating an existing entity. Using `NullValuePropertyMappingStrategy.IGNORE` ensures that null fields in the DTO do not overwrite existing values in the entity, which is essential for `PATCH` operations.

```java
@Mapper(componentModel = "spring")
public interface StudentMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(StudentUpdateDto dto, @MappingTarget Student student);
}
```

## 4. Operational Best Practices

### Transaction Management
Always use `@Transactional` in the service layer when performing operations that modify state, especially when dealing with associations.

### Set vs. List
`Set` is preferred over `List` for `@ManyToMany` relationships to:
1. Prevent duplicate entries.
2. Improve performance of relationship management operations (like `remove`).

---
### Summary of Steps to Implement:
1. Define entities and primary keys.
2. Configure `@ManyToMany` with `@JoinTable` on the owning side.
3. Implement helper methods for bidirectional synchronization.
4. Add `@SoftDelete` for logical deletion.
5. Setup MapStruct for clean API contracts.
6. Use `@Transactional` in Services.
