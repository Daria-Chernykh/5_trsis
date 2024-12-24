package org.example.controller;

import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentPageController {
    @Autowired
    private StudentService StudentService;

    @GetMapping("/students")
    public String getProductsPage(Model model) {
        model.addAttribute("students", StudentService.findAll());
        return "students";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}


