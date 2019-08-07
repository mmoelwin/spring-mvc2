package com.demo.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/home")
    public String welcome(Model model){
        model.addAttribute ("tagline","Welcome Spring mvc");

        return "welcome";
    }

}
