package com.example.demo.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import org.springframework.web.bind.annotation.GetMapping;
@Service
// @Component
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public List<Student> getStudent() {
        return studentRepository.findAll();
                
    }
    public void addNewStudent(Student student){
        Optional<Student>studentByEmail=studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email is taken");
        }
        studentRepository.save(student);
    }
    public void deleteStudent(Long studentId){
        //studentRepository.findById(studentId);
    
        boolean exists=studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                "Student with id"+studentId+"does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,String name,String email){
        Student student=studentRepository.findById(studentId)
        .orElseThrow(()-> new IllegalStateException( "Student with id "+studentId+" doesnt exist"));
        
        if(null !=name && name.length()>0 && !student.getName().equals(name)){
            student.setName(name);
        }

         if(null !=email && email.length()>0 && !!student.getEmail().equals(email)){
            Optional<Student> stuOptional=studentRepository.findStudentByEmail(email);
            if(stuOptional.isPresent()){
                throw new IllegalStateException(email+" is taken");
            }
            student.setEmail(email);
        }
    }

}
