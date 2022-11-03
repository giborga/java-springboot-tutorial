// responsible for data access
package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//will be used in StudentService
@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> { // type of object we want to work this repository with/datatype of id

    // custom function to find user by email
    @Query("SELECT s FROM Student s WHERE s.email = ?1") //JBQL
    Optional<Student> findStudentsByEmail(String email);
}
