package com.example.Lab3;

import com.example.Lab3.Student;

import java.util.List;

public interface Service{

    void create(String fullName, int taskOption, int number, int rating);

    List<Student> getAll();

    List<Student> getByFullName(String fullName);

    boolean delete(int id);
}