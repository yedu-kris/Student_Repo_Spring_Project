package com.example.demo.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args->{
            Student yedu=new Student(
                        "Yedu",
                        "ykjk0087@gmail.com",
                        LocalDate.of(1999, 10, 10));
            Student myu=new Student(
                        "myu",
                        "ykj@gmail.com",
                        LocalDate.of(1999, 10, 10));

            repository.saveAll(List.of(yedu,myu));
        };
    }
}
