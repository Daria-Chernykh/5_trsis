/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPE implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Конструктор объекта Студент
     * @param fullName
     * @param taskOption
     * @param number
     * @param rating
     */
    public StudentPE(String fullName, Integer taskOption, Integer number, Integer rating) {
        this.fullName = fullName;
        this.taskOption = taskOption;
        this.number = number;
        this.rating = rating;
    }

    @Id
    @Column(name = "STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "TASKOPTION")
    private Integer taskOption;
    @Column(name = "NUMBER")
    private Integer number;
    @Column(name = "RATING")
    private Integer rating;
}
