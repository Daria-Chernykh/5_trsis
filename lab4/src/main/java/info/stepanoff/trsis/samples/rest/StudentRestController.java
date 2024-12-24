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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/public/rest/students", produces = "application/json")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)

    @Operation(summary = "Получить перечень студентов",
            description = "Получить перечень студентов - расширенное описание",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    public ResponseEntity<List<StudentDTO>> browse( Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        return ResponseEntity.ok(studentService.listAll());
    }

    @Operation(summary = "Удаление записи о студенте",
            description = "Удаление записи о студенте - расширенное описание",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")
            @Parameter(description = "Идентификатор студента") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        studentService.delete(id);
    }

    @Operation(summary = "Демонстрация работы delete при помощи метода get",
            description = "Демонстрационный метод")
    @RequestMapping(value = "/mockdelete/{id}", method = RequestMethod.GET)
    public void mockdelete(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        studentService.delete(id);
    }

    @Operation(summary = "Создать новую запись о студенте",
            description = "Создать новую запись о студенте - расширенное описание",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<StudentDTO> handleStudentRequest(
            @Parameter(description = "JSON с данными о студенте", required = true)
            @RequestBody StudentDataWithoutID requestBody, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        String fullName = requestBody.getFullName();
        Integer taskOption = requestBody.getTaskOption();
        Integer number = requestBody.getNumber();
        Integer rating = requestBody.getRating();

        return ResponseEntity.ok(studentService.add(fullName, taskOption, number, rating));
    }

    @Operation(summary = "Поиск студента по ФИО",
            description = "Поиск студента по ФИО - расширенное описание",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/fullName/{fullName}", method = RequestMethod.GET)
    public ResponseEntity<StudentDTO> findByFullName(@PathVariable("fullName")
            @Parameter(description = "ФИО студента") String fullName) {
        return ResponseEntity.ok(studentService.findByFullName(fullName));
    }
}
