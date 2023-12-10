package com.example.maincinedrive.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.maincinedrive.model.entity.Sedes;

import com.example.maincinedrive.model.services.SedeServices;

@Controller
@RequestMapping("")
public class SedesController {
    @Autowired
    private SedeServices sedeServices;

    @GetMapping("/sedes")
    public String verSedes(Model model) {

        List<Sedes> sedes = sedeServices.obtenerTodas();

        model.addAttribute("sedes", sedes);
        return "/Sedes/sedes";
    }
}
