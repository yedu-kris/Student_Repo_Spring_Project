package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final Logger LOGGER=LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudent();
    }


    @PostMapping("/register")
    public void  registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        LOGGER.info("studentId",studentId);
        studentService.deleteStudent(studentId);
    }
    @PutMapping(path="{studentId}")

    public void updateStudent(@PathVariable("studentId")Long studentId,
    @RequestParam(required = false)String name,
    @RequestParam(required = false)String email
    ){
        studentService.updateStudent(studentId,name,email);
    }

}
