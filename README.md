# Spring JPA Many-to-Many Lab

A comprehensive demonstration of implementing bidirectional **Many-to-Many** relationships using **Spring Data JPA** and **Hibernate**. This project showcases best practices for managing complex database associations, including soft deletes, DTO mapping, and transactional integrity.

## 🚀 Key Features

- **Bidirectional Many-to-Many Relationship**: Implemented between `Student` and `Course` entities.
- **Association Management**: Custom utility methods in entities to maintain synchronization between both sides of the relationship.
- **Soft Delete**: Uses Hibernate's `@SoftDelete` annotation for logical deletion of records.
- **DTO Pattern**: Decouples API contract from database entities using **MapStruct**.
- **Modern Java**: Built using **Java 25**.
- **In-Memory Database**: Uses **H2** for rapid development and testing.

## 🛠️ Technology Stack

- **Framework**: Spring Boot 4.0.2
- **Language**: Java 25
- **ORM**: Spring Data JPA (Hibernate)
- **Mapping**: MapStruct 1.6.3
- **Utilities**: Lombok
- **Database**: H2 (In-memory)

## 📂 Project Structure

- `com.example.spring_jpa_many_to_many_lab.student`: Student-related logic (Entity, Service, Controller, DTOs, Mapper).
- `com.example.spring_jpa_many_to_many_lab.course`: Course-related logic (Entity, Service, Controller, DTOs, Mapper).

## ⚙️ Getting Started

### Prerequisites
- JDK 25
- Maven 3.9+

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/techboolo/spring-jpa-many-to-many-lab.git
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## 🧪 API Endpoints

### Students
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/students` | Create a new student |
| GET | `/students` | Get all students |
| GET | `/students/{id}` | Get student by ID |
| PUT | `/students/{id}` | Full update of a student |
| PATCH | `/students/{id}` | Partial update (add/remove courses) |
| DELETE | `/students/{id}` | Soft delete a student |

### Courses
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/courses` | Create a new course |
| GET | `/courses` | Get all courses |
| GET | `/courses/{id}` | Get course by ID |
| PUT | `/courses/{id}` | Update course |
| DELETE | `/courses/{id}` | Soft delete a course |

## 📖 Learn More
Check out the [STUDY_GUIDE.md](./STUDY_GUIDE.md) for a deep dive into the implementation details of the Many-to-Many relationship and Soft Delete features.
