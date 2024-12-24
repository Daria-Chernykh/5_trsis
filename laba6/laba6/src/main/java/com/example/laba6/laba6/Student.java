package com.example.laba6.laba6;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "taskOption")
    private int taskOption;
    @Column(name = "number")
    private int number;
    @Column(name = "rating")
    private int rating;


    public Student(String fullName, Integer taskOption, Integer number, Integer rating) {
        this.fullName = fullName;
        this.taskOption = taskOption;
        this.number = number;
        this.rating = rating;
    }

    public Student() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public int getTaskOption() {return taskOption;}

    public void setTaskOption(int taskOption) {this.taskOption = taskOption;}

    public int getNumber() {return number;}

    public void setNumber(int number) {this.number = number;}

    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}
}
