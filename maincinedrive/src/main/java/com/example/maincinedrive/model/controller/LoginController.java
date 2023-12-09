package com.example.maincinedrive.model.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model,
            @RequestParam(value = "logout", required = false) String logout,
            Principal principal, RedirectAttributes attributes) {

        if (error != null) {
            model.addAttribute("error", "Usuario o Contraseña Incorrecto");
        }
        if (principal != null) {
            return "redirect:/home";

        }

        if (logout != null && principal == null) {
            return "redirect:/home";
        }

        return "login-register/login";
    }

}
