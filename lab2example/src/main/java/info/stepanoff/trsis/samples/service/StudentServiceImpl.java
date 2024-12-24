/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.stepanoff.trsis.samples.db.dao.StudentRepository;
import info.stepanoff.trsis.samples.db.model.StudentPE;
import info.stepanoff.trsis.samples.rest.model.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository StudentRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<StudentDTO> listAll() {
        return StudentRepository.findAll().stream()
                .map(StudentPE -> objectMapper.convertValue(StudentPE, StudentDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        StudentRepository.deleteById(id);
    }

    @Override
    public StudentDTO add(String fullName, Integer taskOption, Integer number, Integer rating) {
        return objectMapper.convertValue(StudentRepository.save(new StudentPE(fullName, taskOption, number, rating)), StudentDTO.class);
    }

    @Override
    public StudentDTO findByFullName(String fullName) {
        var StudentPE = StudentRepository.findByFullName(fullName);
        return StudentPE.map(Student -> objectMapper.convertValue(Student, StudentDTO.class)).orElse(null);
    }
}
