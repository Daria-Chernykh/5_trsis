package com.example.laba6.laba6;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<Student> students = studentService.getStudents();
        List<StudentDTO> studentDTOs = students.stream()
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }

    @PostMapping("/add")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            Student student = convertToStudent(studentDTO);
            studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToStudentDTO(student));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteStudent(@RequestParam("fullName") String fullName) {
        if (!studentService.existsByFullName(fullName)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        studentService.deleteStudentByFullName(fullName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    private StudentDTO convertToStudentDTO(Student student) {
        return new StudentDTO(student.getFullName(), student.getTaskOption(), student.getNumber(), student.getRating());
    }

    private Student convertToStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getFullName(), studentDTO.getTaskOption(), studentDTO.getNumber(), studentDTO.getRating());
    }
}
