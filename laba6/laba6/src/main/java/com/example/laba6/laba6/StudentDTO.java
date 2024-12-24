package com.example.laba6.laba6;

public class StudentDTO {
    private String fullName;
    private Integer taskOption;
    private Integer number;
    private Integer rating;

    public StudentDTO(String fullName, Integer taskOption, Integer number, Integer rating) {
        this.fullName = fullName;
        this.taskOption = taskOption;
        this.number = number;
        this.rating = rating;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public Integer getTaskOption() {return taskOption;}

    public void setTaskOption(Integer taskOption) {this.taskOption = taskOption;}

    public Integer getNumber() {return number;}

    public void setNumber(Integer number) {this.number = number;}

    public Integer getRating() {return rating;}

    public void setRating(Integer rating) {this.rating = rating;}
}
