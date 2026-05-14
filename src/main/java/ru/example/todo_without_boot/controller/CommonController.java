package ru.example.todo_without_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class CommonController {

    public String getMainPage(){
        return "main-page";
    }
}
