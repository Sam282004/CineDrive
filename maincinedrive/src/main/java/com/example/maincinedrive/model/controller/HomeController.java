package com.example.maincinedrive.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({ "/", "/index", "/home" })
    public String index() {
        return "home";
    }

    // Ruta cartelera
    @GetMapping("/cartelera")
    public String carteleraPage() {
        return "cartelera/cartelera";
    }

}
