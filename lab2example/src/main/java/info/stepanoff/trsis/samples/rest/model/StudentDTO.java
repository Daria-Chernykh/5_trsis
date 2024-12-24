/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Студент")
public class StudentDTO {

    @Schema(description = "Идентификатор студента")
    private Integer id;
    @Schema(description = "ФИО")
    private String fullName;
    @Schema(description = "Вариант задания")
    private Integer taskOption;
    @Schema(description = "Число сданных лабораторных")
    private Integer number;
    @Schema(description = "Рейтинг")
    private Integer rating;
}
