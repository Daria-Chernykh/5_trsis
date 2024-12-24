package myapp;


public class Group {
    private static int idCounter = 0;
    private int id;
    private String fullName;
    private String taskOption;
    private String number;
    private String rating;

    public Group(String fullName, String taskOption, String number, String rating) {
        this.id = ++idCounter; // Генерация уникального ID
        this.fullName = fullName;
        this.taskOption = taskOption;
        this.number = number;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTaskOption() {
        return taskOption;
    }

    public String getNumber() {
        return number;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", ФИО: " + fullName + ", Вариант задания: " + taskOption + ", Число сданных лабораторных: " + number + ", Рейтинг: " + rating;
    }
}


