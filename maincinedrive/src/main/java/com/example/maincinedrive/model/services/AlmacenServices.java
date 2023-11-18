package com.example.maincinedrive.model.services;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AlmacenServices {

    public void iniciarAlmacenArchivos();

    public String almacenarArchivo(MultipartFile archivo);

    public Path cargarArchivo(String nombreArchivo);

    public Resource cargarRecurso(String nombreArchivo);

    public void eliminarArchivo(String nombreArchivo);
}
