package com.noeliaiglesias.springbootwebflux.app.controllers;

import com.noeliaiglesias.springbootwebflux.app.models.dao.LibroDao;
import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class LibroController {

    private LibroDao libroDao;

    @Autowired
    public LibroController(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @GetMapping({"/listado", "/"})
    public String listar(Model model) {
        Flux<Libro> libros = libroDao.findAll().map(libro -> {
            libro.setTitulo(libro.getTitulo().toUpperCase());
            return libro;
        });
        model.addAttribute("libros", libros);
        model.addAttribute("titulo", "Listado de libros");
        return "listar";
    }

    @GetMapping("/listar-datadriver")
    public String listarDataDriver(Model model) {
        Flux<Libro> libros = libroDao.findAll().map(libro -> {
            libro.setTitulo(libro.getTitulo().toUpperCase());
            return libro;
        }).delayElements(Duration.ofSeconds(1));
        model.addAttribute("libros", new ReactiveDataDriverContextVariable(libros, 2));
        model.addAttribute("titulo", "Listado de libros");
        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model) {
        Flux<Libro> libros = libroDao.findAll().map(libro -> {
            libro.setTitulo(libro.getTitulo().toUpperCase());
            return libro;
        }).repeat(5000);
        model.addAttribute("libros", libros);
        model.addAttribute("titulo", "Listado de libros");
        return "listar";
    }

    @GetMapping("/listar-chunked")
    public String listarChunked(Model model) {
        Flux<Libro> libros = libroDao.findAll().map(libro -> {
            libro.setTitulo(libro.getTitulo().toUpperCase());
            return libro;
        }).repeat(5000);
        model.addAttribute("libros", libros);
        model.addAttribute("titulo", "Listado de libros");
        return "listar-chunked";
    }
}
