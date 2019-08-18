package com.demo.springmvc.controller;

import com.demo.springmvc.model.User;
import com.demo.springmvc.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController  {

    private UserDetailsServiceImpl userDetailsService;

    public AuthController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";    //logical view name under auth folder

    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister( User user,Model model,
                                  RedirectAttributes redirectAttributes){
//        if (result.hasErrors()){
//            model.addAttribute("user",true);
//            return "";
//        }
        userDetailsService.register(user);
        redirectAttributes.addFlashAttribute("register",true);
        return  "redirect:/products";
    }
}
