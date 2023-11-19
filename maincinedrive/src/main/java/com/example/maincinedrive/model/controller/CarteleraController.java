package com.example.maincinedrive.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.maincinedrive.model.entity.Categoria;
import com.example.maincinedrive.model.entity.Genero;
import com.example.maincinedrive.model.entity.Pelicula;
import com.example.maincinedrive.model.repository.CategoriaRepository;
import com.example.maincinedrive.model.repository.GeneroRepository;
import com.example.maincinedrive.model.repository.PeliculaRepository;
import com.example.maincinedrive.model.services.AlmacenServicesImpl;

@Controller
@RequestMapping("/cartelera")
public class CarteleraController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AlmacenServicesImpl almacenServicesImpl;

    @GetMapping("")
    public ModelAndView verCarteleraPage(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {

        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);
        return new ModelAndView("cartelera/cartelera").addObject("peliculas", peliculas);

    }

    @GetMapping("/peliculas/nuevo")
    public ModelAndView mostrarFormularioNuevaPelicula() {
        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
        List<Categoria> categorias = categoriaRepository.findAll();
        return new ModelAndView("cartelera/nueva-pelicula")
                .addObject("pelicula", new Pelicula())
                .addObject("generos", generos)
                .addObject("categoria", categorias);
    }

    @PostMapping("/peliculas/nuevo")
    public ModelAndView registrarPelicula(@Validated Pelicula pelicula, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || pelicula.getPortada().isEmpty()) {
            if (pelicula.getPortada().isEmpty()) {
                bindingResult.rejectValue("portada", "MultipartNotEmpty");
            }
            List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
            List<Categoria> categorias = categoriaRepository.findAll();

            return new ModelAndView("cartelera/nueva-pelicula")
                    .addObject("pelicula", pelicula)
                    .addObject("generos", generos)
                    .addObject("categoria", categorias);
        }

        String rutaPortada = almacenServicesImpl.almacenarArchivo(pelicula.getPortada());
        pelicula.setRutaPortada(rutaPortada);
        peliculaRepository.save(pelicula);

        return new ModelAndView("redirect:/cartelera");

    }

}
