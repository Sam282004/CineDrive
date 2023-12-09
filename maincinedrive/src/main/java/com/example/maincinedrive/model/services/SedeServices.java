package com.example.maincinedrive.model.services;

import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.util.StreamUtils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.Sedes;
import com.example.maincinedrive.model.repository.SedeRepository;

@Service
public class SedeServices {

    @Autowired
    private SedeRepository sedeRepository;

    public List<Sedes> obtenerTodas() {
        return sedeRepository.findAll();
    }

    public List<Sedes> obtenerListaDeSedes() {
        return sedeRepository.findAll();
    }

    public void crearSedes() {
        if (sedeRepository.count() == 0) {
            crearSede("Sede Norte", "123 Avenida Norte", "5:00 PM - 10:00 PM", "images/sedes/sede_norte.png");
            crearSede("Sede Surco", "456 Calle Principal", "5:00 PM - 10:00 PM", "images/sedes/sede_surco.png");
            crearSede("Sede Ate", "789 Calle Ate", "5:00 PM - 10:00 PM", "images/sedes/sede_ate.png");
            crearSede("Sede Callao", "101 Avenida Callao", "5:00 PM - 10:00 PM", "images/sedes/sede_callao.png");
        } else {
            System.out.println("Los datos ya existen en la base de datos. No se insertarán nuevamente.");
        }
    }

    private void crearSede(String nombre, String direccion, String horarios, String rutaImagen) {
        String rutaAbsoluta = Paths.get("").toAbsolutePath().toString().replace("\\", "/");
        System.out.println("Intentando leer desde la ruta: " + rutaAbsoluta + "/" + rutaImagen);
        byte[] imagenBytes = obtenerBytesDeImagen(rutaImagen);
        if (imagenBytes != null) {
            System.out.println("Bytes de imagen obtenidos correctamente.");
        } else {
            System.out.println("No se pudieron obtener los bytes de la imagen.");
        }

        if (imagenBytes != null) {
            Sedes sede = new Sedes();
            sede.setNombre(nombre);
            sede.setDireccion(direccion);
            sede.setHorarios_operacion(horarios);
            sede.setImagen(imagenBytes);

            sedeRepository.save(sede);
        } else {
            System.out.println("No se pudieron leer los bytes de la imagen.");
        }
    }

    private byte[] obtenerBytesDeImagen(String rutaImagen) {
        try {
            Resource resource = new ClassPathResource("static/" + rutaImagen);
            try (InputStream inputStream = resource.getInputStream()) {
                return StreamUtils.copyToByteArray(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer la imagen.");
            return null;
        }
    }

    public Sedes obtenerSedePorNombre(String nombreSede) {
        return sedeRepository.findByNombre(nombreSede);
    }

}
