package com.example.Lab3;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SimpleController {
    private final Service service;
    @Autowired
    public SimpleController(Service service){
        this.service = service;
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Student student, BindingResult bindingResult, Model model){
        service.create(student.getFullName(), student.getTaskOption(), student.getNumber(), student.getRating());
        return "redirect:/main";
    }

    @GetMapping("/students/{fullName}")
    public ModelAndView getFullName(@PathVariable(name="group") String fullName){
        final List<Student> students = service.getByFullName(fullName);
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("students",service.getAll());
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        final boolean deleted = service.delete(id);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public ModelAndView getMainPage(){
        int dbsize = DBHelper.db.stream().mapToInt(Student::getId).max().orElse(0);;
        Student student = new Student(dbsize + 1,"",0,0,0);
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("students",service.getAll());
        mav.addObject("student",student);
        return mav;
    }
}
