package com.example.maincinedrive.model.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.maincinedrive.model.dto.UsuarioRegistroDTO;

import com.example.maincinedrive.model.services.UsuarioServices;

@Controller
@RequestMapping("/registrar")
public class RegistroController {
    private UsuarioServices usuarioServicio;

    public RegistroController(UsuarioServices usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro(Principal principal) {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO,
            RedirectAttributes attribute) {
        usuarioServicio.guardarUsuario(registroDTO);
        attribute.addFlashAttribute("success", "Usuario Registrado Correctamente");
        return "redirect:/registro?exito";
    }

}