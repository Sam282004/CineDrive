package com.example.maincinedrive.model.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.maincinedrive.model.entity.Usuario;
import com.example.maincinedrive.model.repository.UsuarioRepository;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ModelAttribute("nombre")
    public String addNombreToModel(Principal principal) {
        if (principal != null) {
            String emailUsuario = principal.getName();
            Usuario usuario = usuarioRepository.findByEmail(emailUsuario);
            if (usuario != null) {
                return usuario.getNombre();
            }
        }
        return null;
    }
}