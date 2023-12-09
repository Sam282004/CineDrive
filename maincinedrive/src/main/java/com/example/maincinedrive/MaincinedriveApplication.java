package com.example.maincinedrive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.maincinedrive.model.entity.Asiento;
import com.example.maincinedrive.model.entity.EstadoAsiento;
import com.example.maincinedrive.model.entity.Horario;
import com.example.maincinedrive.model.entity.HorarioPelicula;
import com.example.maincinedrive.model.entity.Pelicula;
import com.example.maincinedrive.model.entity.Sedes;
import com.example.maincinedrive.model.services.AsientoService;
import com.example.maincinedrive.model.services.PeliculaServices;
import com.example.maincinedrive.model.services.SedeServices;

@SpringBootApplication
public class MaincinedriveApplication {

	@Autowired
	private SedeServices sedeService;

	@Autowired
	private PeliculaServices peliculaServices;

	@Autowired
	private AsientoService asientoService;

	public static void main(String[] args) {
		SpringApplication.run(MaincinedriveApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ApplicationContext context) {
		return args -> {
			System.out.println("Iniciando la creación de sedes...");
			sedeService.crearSedes();
			System.out.println("Creación de sedes completada.");

			List<Pelicula> peliculas = peliculaServices.obtenerTodas();
			List<Horario> horarios = peliculaServices.obtenerTodosLosHorarios();
			List<Sedes> sedes = peliculaServices.obtenerTodasLasSedes();

			// Verificar si ya existen asientos para cada sede
			for (Sedes sede : sedes) {
				List<Asiento> asientosExistentes = asientoService.obtenerAsientoPorSede(sede);

				if (asientosExistentes.isEmpty()) {
					// Crear 20 asientos solo si no hay asientos existentes
					List<Asiento> asientos = new ArrayList<>();
					for (int i = 1; i <= 20; i++) {
						Asiento asiento = new Asiento();
						asiento.setSede(sede);
						asiento.setNombreAsiento("Asiento " + i);
						asiento.setEstado(EstadoAsiento.DISPONIBLE);
						asientos.add(asiento);
					}
					asientoService.guardarAsientos(asientos);

				}
			}
			// Asignar automáticamente las películas a los horarios y sedes específicos
			for (Pelicula pelicula : peliculas) {
				for (Horario horario : horarios) {
					for (Sedes sede : sedes) {
						HorarioPelicula horarioPelicula = new HorarioPelicula();
						horarioPelicula.setPelicula(pelicula);
						horarioPelicula.setSede(sede);
						horarioPelicula.setHorario(horario);
						horarioPelicula.setDuracion(pelicula.getDuracionPelicula());
						horarioPelicula.setAsientosDisponibles(20);
						horarioPelicula.setEstadoProyeccion("No Proyectada");
						horarioPelicula.setFormatoProyeccion("3D");
						horarioPelicula.setPrecioEntrada(new BigDecimal("20.00"));

						// Aquí es donde deberías establecer horario_inicio correctamente
						horarioPelicula.setHoraInicio(horario.getHoraInicio());

						// Llamada al método de asignación en el servicio de películas
						peliculaServices.asignarHorarioPelicula(horarioPelicula);
					}
				}
			}
		};
	}
}