package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.StudentDTO;
import org.example.model.Student;
import org.example.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/students")
public class StudentAPIController {

    @Autowired
    private StudentService service;

    @Autowired
    private ModelMapper modelMapper;

    private StudentDTO convertToDTO(Student product) {
        return modelMapper.map(product, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto) {
        return modelMapper.map(dto, Student.class);
    }

    // Получение текущего языка из запроса
    private String getCurrentLanguage() {
        Locale locale = LocaleContextHolder.getLocale();  // Получаем текущую локаль
        return locale.getLanguage();
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = service.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        // Создаем заголовки с языком
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Language", getCurrentLanguage());

        // Возвращаем список устройств с заголовками
        return ResponseEntity.ok()
                .headers(headers)
                .body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> student = service.findById(id);
        if (student.isPresent()) {
            // Создаем заголовки с языком
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Language", getCurrentLanguage());

            // Возвращаем устройство с заголовками
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(convertToDTO(student.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> add(
            @Valid
            @RequestBody StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        student.setId(null);
        Student addedStudent = service.save(student);

        // Создаем заголовки с языком
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Language", getCurrentLanguage());

        // Возвращаем созданное устройство с заголовками
        return new ResponseEntity<>(convertToDTO(addedStudent), headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (service.deleteById(id)) {
            // Создаем заголовки с языком
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Language", getCurrentLanguage());

            // Возвращаем ответ с заголовками
            return ResponseEntity.noContent()
                    .headers(headers)
                    .build();
        }
        return ResponseEntity.notFound().build();
    }
}
