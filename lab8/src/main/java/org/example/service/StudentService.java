package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository StudentRepository;

    @Autowired
    public StudentService(StudentRepository StudentRepository) {
        this.StudentRepository = StudentRepository;
    }

    @Transactional()
    public List<Student> findAll() {
        return StudentRepository.findAll();
    }

    @Transactional
    public Student save(Student student) {
        return StudentRepository.save(student);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (StudentRepository.existsById(id)) {
            StudentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional()
    public Optional<Student> findById(Long id) {
        return StudentRepository.findById(id);
    }
}