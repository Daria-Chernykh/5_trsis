package myapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {

    private List<Group> devices = new ArrayList<>(); // Список для хранения устройств

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println("<h1>Ведение списка группы</h1>");

        // Ссылка на главную страницу
        response.getWriter().println("<p><a href='/lab1/index.html'>Главная страница</a></p>");

        // Форма для добавления нового устройства
        response.getWriter().println("<h2>Добавление нового студента</h2>");
        response.getWriter().println("<form action='group' method='POST'>" +
                "ФИО:                        <input type='text' name='fullName'><br>" +
                "Вариант задания:            <input type='text' name='taskOption'><br>" +
                "Число сданных лабораторных: <input type='text' name='number'><br>" +
                "Рейтинг:                    <input type='text' name='rating'><br>" +
                "<input type='hidden' name='action' value='add'>" +
                "<input type='submit' value='Добавить студента'>" +
                "</form>");

        // Список устройств и формы для их удаления
        response.getWriter().println("<h2>Список группы</h2>");
        if (devices.isEmpty()) {
            response.getWriter().println("<p>Нет студентов.</p>");
        } else {
            response.getWriter().println("<ul>");
            for (Group device : devices) {
                response.getWriter().println("<li>" + device +
                        " <form action='group' method='POST' style='display:inline;'>" +
                        "<input type='hidden' name='action' value='delete'>" +
                        "<input type='hidden' name='id' value='" + device.getId() + "'>" +
                        "<input type='submit' value='Удалить'>" +
                        "</form></li>");
            }
            response.getWriter().println("</ul>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // Добавление нового устройства
        if ("add".equals(action)) {
            String fullName = request.getParameter("fullName");
            String taskOption = request.getParameter("taskOption");
            String number = request.getParameter("number");
            String rating = request.getParameter("rating");

            // Создаем новое устройство и добавляем его в список
            Group newDevice = new Group(fullName, taskOption, number, rating);
            devices.add(newDevice);
        }

        // Удаление устройства по ID
        if ("delete".equals(action)) {
            int deviceId = Integer.parseInt(request.getParameter("id"));
            devices.removeIf(device -> device.getId() == deviceId);
        }

        // После обработки данных перенаправляем пользователя обратно на главную страницу
        response.sendRedirect("/lab1/group");
    }
}


