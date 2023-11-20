package com.example.maincinedrive.model.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.maincinedrive.model.entity.Pelicula;
import com.example.maincinedrive.model.repository.PeliculaRepository;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("")
public class HomeController {

        @Autowired
        private PeliculaRepository peliculaRepository;

        // @GetMapping({ "/", "/index", "/home" })
        // public String index() {
        // return "home";
        // }
        @GetMapping({ "/", "/index", "/home" })
        public ModelAndView verPaginaPeliculas(
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEstreno) {
                if (fechaEstreno == null) {
                        fechaEstreno = LocalDate.now();
                }

                List<Pelicula> peliculasEstreno = peliculaRepository.findByFechaEstrenoAfter(fechaEstreno);
                List<Pelicula> peliculasNoEstreno = peliculaRepository
                                .findByFechaEstrenoBeforeAndFechaEstrenoIsNotNull(LocalDate.now());

                return new ModelAndView("home")
                                .addObject("peliculasEstreno", peliculasEstreno)
                                .addObject("peliculasNoEstreno", peliculasNoEstreno)
                                .addObject("fechaEstreno", fechaEstreno);
        }

        @GetMapping("/cartelera/listados-pelicula")
        public ModelAndView carteleraFullPage(
                        @PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.ASC) Pageable pageable) {

                Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);
                return new ModelAndView("cartelera/cartelera")
                                .addObject("peliculas", peliculas);

        }

        @GetMapping("/peliculas/{id}")
        public ModelAndView detallePelicula(@PathVariable Integer id) {
                Pelicula pelicula = peliculaRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encontró la película con ID: " + id));

                return new ModelAndView("cartelera/detalle-pelicula")
                                .addObject("pelicula", pelicula);

        }

}
