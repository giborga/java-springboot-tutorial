// API layer
package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //serves REST endpoints
@RequestMapping(path="api/v1/student") //http://localhost:8081/api/v1/student
public class StudentController {

    private final StudentService studentService; // reference to StudentService to use methods and add it to the constructor

    // Spring container “injects” objects into other objects or “dependencies”.
    @Autowired // says that studentService will be instantiated for us
    public StudentController(StudentService studentService) { // inject studentService inside the controller
        this.studentService = studentService; // to make it work we need an instance of StudentService, otherwise: "Could not autowire. No beans of 'StudentService' type found. "
    }

    @GetMapping // get endpoint (that returns an array of students)
    public List<Student> getStudents() {
        return studentService.getStudents(); //use method from student service
    }

    // if email does not exist - add to db
    // else - throw an exception
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) { //map this new Student with json request body we pass
        studentService.addNewStudent(student);
    };

    // if student id exists - delete, else - illegal exception
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
