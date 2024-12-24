package org.example.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "STUDENT")
@Data
public class Student {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "TASKOPTION")
    private Integer taskOption;
    @Column(name = "NUMBER")
    private Integer number;
    @Column(name = "RATING")
    private Integer rating;

    // Конструкторы
    public Student(String fullName, int taskOption, int number, int rating) {
        this.fullName = fullName;
        this.taskOption = taskOption;
        this.number = number;
        this.rating = rating;
    }

    public Student() {
    }

    // Getters и Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getTaskOption() { return taskOption; }
    public void setTaskOption(int taskOption) { this.taskOption = taskOption; }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

}

