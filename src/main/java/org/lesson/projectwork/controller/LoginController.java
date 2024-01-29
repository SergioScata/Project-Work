package org.lesson.projectwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/login")
public class LoginController {
    @GetMapping
    public String home(){

        return "home/home";
    }

}
