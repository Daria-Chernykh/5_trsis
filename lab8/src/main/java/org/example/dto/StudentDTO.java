package org.example.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class StudentDTO {
    private Long id;

    @NotBlank(message = "ФИО студента не может быть пустым")
    private String fullName;

    @NotBlank(message = "Вариант задания студента не может быть пустым")
    private int taskOption;

    @NotBlank(message = "Число сданных лабораторных студента не может быть пустым")
    private int number;

    @NotBlank(message = "Рейтинг студента не может быть пустым")
    private int rating;

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public int getTaskOption() { return taskOption; }
    public int getNumber() { return number; }
    public int getRating() { return rating; }
}
