package com.example.demo;

import com.example.demo.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final List<Student> studentList;

    public StudentService(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void insert(Student student) {
        studentList.add(student);
    }

    public List<Student> selectAll() {
        return studentList;
    }

    public Student selectById(int id) {
        return studentList.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean updateById(int id, Student updated) {
        Optional<Student> optional = studentList.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        if (optional.isPresent()) {
            Student existing = optional.get();
            existing.setFullName(updated.getFullName());
            existing.setGender(updated.getGender());
            existing.setScore(updated.getScore());
            return true;
        }
        return false;
    }

    public boolean deleteById(int id) {
        return studentList.removeIf(s -> s.getId() == id);
    }
}

