package com.example.demo;


import com.example.demo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InMemoryDatabase {

    @Bean
    public List<com.example.demo.Student> studentList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Thida", "Female", 85.0));
        students.add(new Student(2, "Heng", "Male", 78.5));
        students.add(new Student(3, "Thai", "Male", 92.0));
        students.add(new Student(4, "Nana", "Female", 88.0));
        students.add(new Student(5, "Vantho", "Male", 74.5));
        students.add(new Student(6, "Sok", "Female", 90.0));
        students.add(new Student(7, "Sin", "Male", 80.0));
        students.add(new Student(8, "Vong", "Female", 95.0));
        students.add(new Student(9, "Ching", "Male", 70.0));
        students.add(new Student(10, "Jok", "Female", 89.5));
        return students;
    }
}
