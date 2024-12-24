/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.rest.model.StudentDTO;
import info.stepanoff.trsis.samples.rest.model.StudentDataWithoutID;
import info.stepanoff.trsis.samples.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/public/rest/students", produces = "application/json; charset=UTF-8")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService StudentService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")

    @Operation(summary = "Получить перечень студентов",
            description = "Получить перечень студентов - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    public ResponseEntity<List<StudentDTO>> browse() {
        return ResponseEntity.ok(StudentService.listAll());
    }

    @Operation(summary = "Удаление записи о студенте",
            description = "Удаление записи о студенте - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")
            @Parameter(description = "Идентификатор студента") Integer id) {
        StudentService.delete(id);
    }

    @Operation(summary = "Создать новую запись о студенте",
            description = "Создать новую запись о студенте - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<StudentDTO> handleStudentRequest(
            @Parameter(description = "JSON с данными о студенте", required = true)
            @RequestBody StudentDataWithoutID requestBody) {

        String fullName = requestBody.getFullName();
        Integer taskOption = requestBody.getTaskOption();
        Integer number = requestBody.getNumber();
        Integer rating = requestBody.getRating();

        return ResponseEntity.ok(StudentService.add(fullName, taskOption, number, rating));
    }

    @Operation(summary = "Поиск студента по ФИО",
            description = "Поиск студента по ФИО - расширенное описание",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Успешное выполнение"),
                    @ApiResponse(responseCode = "401",
                            description = "Требуется аутентификация"),
                    @ApiResponse(responseCode = "403",
                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                    @ApiResponse(responseCode = "404",
                            description = "Ресурс не найден")
            })
    @RequestMapping(value = "/fullName/{fullName}", method = RequestMethod.GET)
    public ResponseEntity<StudentDTO> findByFullName(@PathVariable("fullName")
                                                     @Parameter(description = "ФИО") String fullName) {
        return ResponseEntity.ok(StudentService.findByFullName(fullName));
    }
}
