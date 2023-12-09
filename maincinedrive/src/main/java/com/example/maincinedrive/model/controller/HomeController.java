package com.example.maincinedrive.model.controller;

import java.security.Principal;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.maincinedrive.model.dto.UsuarioRegistroDTO;
import com.example.maincinedrive.model.entity.Asiento;
import com.example.maincinedrive.model.entity.Comida;

import com.example.maincinedrive.model.entity.Pelicula;
import com.example.maincinedrive.model.entity.Sedes;

import com.example.maincinedrive.model.repository.PeliculaRepository;

import com.example.maincinedrive.model.services.AsientoService;
import com.example.maincinedrive.model.services.ComidaService;

// import com.example.maincinedrive.model.services.SedeServices;
import com.example.maincinedrive.model.services.SedeServices;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("")
public class HomeController {

        @Autowired
        private PeliculaRepository peliculaRepository;

        @Autowired
        private SedeServices sedeService;

        @Autowired
        private AsientoService asientoService;

        @Autowired
        private ComidaService comidaService;

        private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

        @GetMapping("/registro")
        public String registro(Model model, Principal principal) {
                if (principal != null) {
                        return "redirect:/home";
                }
                model.addAttribute("usuario", new UsuarioRegistroDTO());
                return "login-register/register";
        }

        @GetMapping({ "/", "/index", "/home" })
        public String verPaginaPeliculas(
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEstreno,
                        Model model) {

                if (fechaEstreno == null) {
                        fechaEstreno = LocalDate.now();
                }

                List<Pelicula> peliculasEstreno = peliculaRepository.findByFechaEstrenoAfter(fechaEstreno);
                List<Pelicula> peliculasNoEstreno = peliculaRepository
                                .findByFechaEstrenoBeforeAndFechaEstrenoIsNotNull(LocalDate.now());

                model.addAttribute("peliculasEstreno", peliculasEstreno);
                model.addAttribute("peliculasNoEstreno", peliculasNoEstreno);
                model.addAttribute("fechaEstreno", fechaEstreno);

                return "home";
        }

        @GetMapping("/cartelera/listados-pelicula")
        public ModelAndView carteleraFullPage(
                        @PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.ASC, size = 9) Pageable pageable,
                        @RequestParam(name = "genero", required = false) String genero,
                        RedirectAttributes redirectAttributes) {
                try {
                        logger.debug("Pageable: {}", pageable);
                        logger.debug("Parámetro genero: {}", genero);

                        Page<Pelicula> peliculas;

                        if (genero != null && !genero.isEmpty()) {
                                peliculas = peliculaRepository.findByGenerosTitulo(genero, pageable);
                        } else {
                                peliculas = peliculaRepository.findAll(pageable);
                        }

                        logger.debug("Número de elementos en la página: {}", peliculas.getNumberOfElements());

                        ModelAndView modelAndView = new ModelAndView("cartelera/cartelera")
                                        .addObject("peliculas", peliculas)
                                        .addObject("generoSeleccionado", genero);

                        // Agregar parámetros de filtro a la redirección
                        redirectAttributes.addAttribute("genero", genero);
                        redirectAttributes.addAttribute("page", pageable.getPageNumber());

                        return modelAndView;
                } catch (Exception e) {
                        logger.error("Error al procesar la solicitud", e);
                        // Devolver una página de error o redirigir a una página de error.
                        return new ModelAndView("error");
                }
        }

        @GetMapping("/peliculas/{id}")
        public ModelAndView detallePelicula(@PathVariable Integer id,
                        @RequestParam(required = false, defaultValue = "1") Integer idSede) {
                Pelicula pelicula = peliculaRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No se encontró la película con ID: " + id));

                // Obtener información adicional (sedes, comidas, etc.)
                List<Comida> comidas = comidaService.obtenerListaDeComidas();
                List<Sedes> sedes = sedeService.obtenerTodas();
                Map<Integer, List<Asiento>> asientosPorSede = new HashMap<>();

                for (Sedes sede : sedes) {
                        List<Asiento> asientos = asientoService.obtenerAsientosPorSede(sede.getId());
                        asientosPorSede.put(sede.getId(), asientos);
                }

                ModelAndView modelAndView = new ModelAndView("cartelera/detalle-pelicula")
                                .addObject("pelicula", pelicula)
                                .addObject("comidas", comidas)
                                .addObject("sedes", sedes)
                                .addObject("asientosPorSede", asientosPorSede);

                return modelAndView;
        }
}
