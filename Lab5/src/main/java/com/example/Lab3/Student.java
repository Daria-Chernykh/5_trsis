package com.example.Lab3;

import java.io.Serializable;

public class Student {
    private int id;
    private String fullName;
    private int taskOption;
    private int number;
    private int rating;
    public Student(int id, String fullName, int taskOption, int number, int rating){
        this.id = id;
        this.fullName = fullName;
        this.taskOption = taskOption;
        this.number = number;
        this.rating = rating;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public int getTaskOption(){
        return taskOption;
    }
    public void setTaskOption(int taskOption){
        this.taskOption = taskOption;
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating = rating;
    }
}
