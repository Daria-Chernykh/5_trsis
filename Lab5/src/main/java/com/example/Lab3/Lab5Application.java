package com.example.Lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab5Application {

    public static void main(String[] args) {
        DBHelper.db.add(new Student(1, "Черных Дарья Юрьевна", 1, 21, 2));
        DBHelper.db.add(new Student(2, "Ким Намджун", 7, 30, 1));
        DBHelper.db.add(new Student(3, "Ким Сокджин", 7, 32, 1));
        DBHelper.db.add(new Student(4, "Мин Юнги", 7, 31, 1));
        DBHelper.db.add(new Student(5, "Чон Хосок", 7, 30, 1));
        DBHelper.db.add(new Student(6, "Пак Чимин", 7, 29, 1));
        DBHelper.db.add(new Student(7, "Ким Техен", 7, 28, 1));
        DBHelper.db.add(new Student(8, "Чон Чонгук", 7, 27, 1));
        SpringApplication.run(Lab5Application.class, args);
    }
}
