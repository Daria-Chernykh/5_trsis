package org.example.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.StudentDTO;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaConsumerService {

    @Autowired
    private StudentRepository StudentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ModelMapper modelMapper;

    public KafkaConsumerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @KafkaListener(topics = "students", groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void consume(String message) {
        try {
            System.out.println("Получено сообщение от Kafka: " + message);

            // Парсим сообщение как JSON
            JsonNode jsonNode = objectMapper.readTree(message);
            String action = jsonNode.get("action").asText();

            if ("delete".equals(action)) {

                // Обработка удаления
                Long id = jsonNode.get("id").asLong();
                if (StudentRepository.existsById(id)) {
                    StudentRepository.deleteById(id);
                    System.out.println("Студент с ID " + id + " удален.");
                }
            } else if ("create".equals(action)) {
                // Обработка создания/обновления
                StudentDTO studentDTO = objectMapper.treeToValue(jsonNode.get("data"), StudentDTO.class);
                Student student = convertToEntity(studentDTO);

                if (!StudentRepository.existsById(student.getId())) {
                    StudentRepository.save(student);
                    System.out.println("Студент добавлен: " + student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Student convertToEntity(StudentDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
}

