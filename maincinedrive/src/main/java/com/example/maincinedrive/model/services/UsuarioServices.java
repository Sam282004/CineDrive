package com.example.maincinedrive.model.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.maincinedrive.model.dto.UsuarioRegistroDTO;
import com.example.maincinedrive.model.entity.Usuario;

public interface UsuarioServices extends UserDetailsService {

    Usuario guardarUsuario(UsuarioRegistroDTO registroDTO);
}
