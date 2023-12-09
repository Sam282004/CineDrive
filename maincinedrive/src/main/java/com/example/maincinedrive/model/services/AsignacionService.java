// package com.example.maincinedrive.model.services;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.maincinedrive.model.entity.Horario;
// import com.example.maincinedrive.model.entity.Pelicula;
// import com.example.maincinedrive.model.entity.Sedes;
// import com.example.maincinedrive.model.repository.PeliculaRepository;

// @Service
// public class AsignacionService {

// @Autowired
// private HorarioPeliculaService horarioPeliculaService;
// @Autowired
// private PeliculaServices peliculaService;

// @Autowired
// private SedeServices sedeService;

// @Autowired
// private HorarioServices horarioService;

// // Otros servicios necesarios...

// public void asignarHorariosDeProyeccion() {
// List<Pelicula> peliculas = peliculaService.obtenerTodas();
// List<Sedes> sedes = sedeService.obtenerTodas();
// List<Horario> horarios = horarioService.obtenerTodos();

// int indexPelicula = 0;

// for (Horario horario : horarios) {
// for (Sedes sede : sedes) {
// Pelicula pelicula = peliculas.get(indexPelicula);
// horarioPeliculaService.asignarHorarioPelicula(pelicula, horario, sede);
// indexPelicula = (indexPelicula + 1) % peliculas.size();
// }
// }
// }

// }