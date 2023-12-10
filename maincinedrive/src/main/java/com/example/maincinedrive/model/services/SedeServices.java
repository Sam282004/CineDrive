package com.example.maincinedrive.model.services;

import java.util.List;

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
            crearSede("Sede Norte", "123 Avenida Norte", "5:00 PM - 10:00 PM", "sede_norte.png",
                    "Sumérgete en la experiencia única del Autocinema en nuestra Sede Norte. Con una pantalla al aire libre y tecnología de proyección de vanguardia, disfruta de películas bajo las estrellas desde la comodidad de tu vehículo. Una fusión de entretenimiento y comodidad, la Sede Norte Autocinema te invita a vivir la magia del cine en un ambiente al aire libre.");
            crearSede("Sede Surco", "456 Calle Principal", "5:00 PM - 10:00 PM", "sede_surco.png",
                    "La Sede Surco Autocinema redefine la manera en que experimentas el séptimo arte. Enclavada en un entorno tranquilo, ofrece no solo proyecciones cinematográficas de calidad, sino también un espacio relajado para disfrutar de películas en compañía. Suma momentos inolvidables al aire libre en el Autocinema Sede Surco.");
            crearSede("Sede Ate", "789 Calle Ate", "5:00 PM - 10:00 PM", "sede_ate.png",
                    "Explora la fusión de la tecnología y la diversión en el Autocinema de la Sede Ate. Con pantallas gigantes y un sistema de sonido envolvente, este espacio transforma el acto de ver películas en una experiencia emocionante. En la Sede Ate Autocinema, cada función es una aventura cinematográfica.");
            crearSede("Sede Callao", "101 Avenida Callao", "5:00 PM - 10:00 PM", "sede_callao.png",
                    "Suma emociones al Autocinema de la Sede Callao, donde la brisa marina y las proyecciones cinematográficas se encuentran. Experimenta la magia del cine desde la comodidad de tu automóvil en un entorno costero único. La Sede Callao Autocinema te invita a disfrutar de películas con el sonido de las olas de fondo.");
        } else {
            System.out.println("Los datos ya existen en la base de datos. No se insertarán nuevamente.");
        }
    }

    private void crearSede(String nombre, String direccion, String horarios, String rutaImagen, String descripcion) {

        if (rutaImagen != null) {
            Sedes sede = new Sedes();
            sede.setNombre(nombre);
            sede.setDireccion(direccion);
            sede.setHorarios_operacion(horarios);
            sede.setImagen(rutaImagen);
            sede.setDescripcin(descripcion);

            sedeRepository.save(sede);
        } else {
            System.out.println("No se pudieron leer los bytes de la imagen.");
        }
    }

    public Sedes obtenerSedePorNombre(String nombreSede) {
        return sedeRepository.findByNombre(nombreSede);
    }

}
