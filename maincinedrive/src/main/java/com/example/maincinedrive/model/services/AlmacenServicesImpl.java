package com.example.maincinedrive.model.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.maincinedrive.model.exceptions.AlmacenException;
import com.example.maincinedrive.model.exceptions.FileNotFoundException;

import jakarta.annotation.PostConstruct;

@Service
public class AlmacenServicesImpl implements AlmacenServices {

    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct

    @Override
    public void iniciarAlmacenArchivos() {

        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException exception) {
            throw new AlmacenException("Error al Iniciar ");
        }
    }

    @Override
    public String almacenarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        if (archivo.isEmpty()) {
            throw new AlmacenException("No se puede almacenar un archivo vacio");
        }
        try {
            InputStream inputStream = archivo.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new AlmacenException("Error al almacenar el archivo " + nombreArchivo, exception);
        }

        return nombreArchivo;
    }

    @Override
    public Path cargarArchivo(String nombreArchivo) {

        return Paths.get(storageLocation).resolve(nombreArchivo);
    }

    @Override
    public Resource cargarRecurso(String nombreArchivo) {

        try {
            Path archivo = cargarArchivo(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());

            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new FileNotFoundException("No se pudo encontrar el archivo " + nombreArchivo);
            }

        } catch (MalformedURLException exception) {
            throw new FileNotFoundException("No se pudo encontrar el archivo " + nombreArchivo, exception);

        }

    }

    @Override
    public void eliminarArchivo(String nombreArchivo) {

        Path archivo = cargarArchivo(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

}
