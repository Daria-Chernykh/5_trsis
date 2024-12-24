package com.example.laba6.laba6;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public boolean existsByFullName(String fullName) {
        return studentRepository.existsByFullName(fullName);
    }

    @Transactional
    public void deleteStudentByFullName(String fullName) {
        studentRepository.deleteByFullName(fullName);
    }
}
