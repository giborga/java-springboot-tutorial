//Business logic for managing students

package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
// StudentService has to be a spring bean -> needs to be instantiated -> so StudentController knows how to find StudentService class
public class StudentService {

    //dependency injection
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) { //constructor
        this.studentRepository = studentRepository;
    }

    //+ why autowired is not above 'public StudentService'?
    // methods
    @Autowired
    public List<Student> getStudents() { // instead of adding a list here ->
        return studentRepository.findAll(); // will return a list
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("place is taken");
        }
        studentRepository.save(student);
    };

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student does not exist");
        }
        studentRepository.deleteById(id);
    }

    // transactional - dont have to implement jbql
    //use service to update entity if its possible
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student doesnt exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getName(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
