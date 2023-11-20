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
import org.springframework.web.bind.annotation.PathVariable;
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

import jakarta.persistence.EntityNotFoundException;

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
        return new ModelAndView("cartelera/listado-pelicula")
                .addObject("peliculas", peliculas);

    }

    // @GetMapping("/cartelera")
    // public ModelAndView carteleraPageAll() {
    // return new ModelAndView("cartelera/cartelera");
    // }

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

    @GetMapping("/peliculas/{id}/editar")
    public ModelAndView editarPelicula(@PathVariable Integer id) {

        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la película con ID: " + id));
        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
        List<Categoria> categorias = categoriaRepository.findAll();

        return new ModelAndView("cartelera/editar-pelicula")
                .addObject("pelicula", pelicula)
                .addObject("generos", generos)
                .addObject("categoria", categorias);
    }

    @PostMapping("/peliculas/{id}/editar")
    public ModelAndView actualizarPelicula(@PathVariable Integer id, @Validated Pelicula pelicula,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
            List<Categoria> categorias = categoriaRepository.findAll();
            return new ModelAndView("cartelera/editar-pelicula")
                    .addObject("pelicula", pelicula)
                    .addObject("generos", generos)
                    .addObject("categoria", categorias);
        }
        Pelicula peliculaDB = peliculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la película con ID: " + id));
        peliculaDB.setTitulo(pelicula.getTitulo());
        peliculaDB.setSinopsis(pelicula.getSinopsis());
        peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());
        peliculaDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
        peliculaDB.setGeneros(pelicula.getGeneros());
        peliculaDB.setCategoria(pelicula.getCategoria());
        peliculaDB.setDuracionPelicula(pelicula.getDuracionPelicula());

        if (!pelicula.getPortada().isEmpty()) {
            almacenServicesImpl.eliminarArchivo(peliculaDB.getRutaPortada());
            String rutaPortada = almacenServicesImpl.almacenarArchivo(pelicula.getPortada());
            peliculaDB.setRutaPortada(rutaPortada);

        }
        peliculaRepository.save(peliculaDB);

        return new ModelAndView("redirect:/cartelera");

    }

    @PostMapping("/peliculas/{id}/eliminar")
    public String eliminarPelicula(@PathVariable Integer id) {

        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la película con ID: " + id));

        peliculaRepository.delete(pelicula);
        almacenServicesImpl.eliminarArchivo(pelicula.getRutaPortada());
        return "redirect:/cartelera";

    }
}
