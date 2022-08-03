package com.noeliaiglesias.springbootwebflux.app.controllers;

import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import com.noeliaiglesias.springbootwebflux.app.models.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping({"/listado", "/"})
    public String listar(Model model) {
        Flux<Libro> libros = this.libroService.findAllNombreUpperCase();
        model.addAttribute("libros", libros);
        model.addAttribute("titulo", "Listado de libros");
        return "listar";
    }

    @GetMapping("/crear")
    public Mono<String> crear(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("titulo", "Crear libro");
        return Mono.just("crear");
    }

    @PostMapping("/crear")
    public Mono<String> crear(Libro libro) {
        return this.libroService.save(libro).thenReturn("redirect:/listado");
    }

    @GetMapping("/listar-datadriver")
    public String listarDataDriver(Model model) {
        Flux<Libro> libros = libroService.findAllNombreUpperCase().delayElements(Duration.ofSeconds(1));
        model.addAttribute("libros", new ReactiveDataDriverContextVariable(libros, 2));
        model.addAttribute("titulo", "Listado de libros");
        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model) {
        Flux<Libro> libros = libroService.findAllNombreUpperCaseRepeat();
        model.addAttribute("libros", libros);
        model.addAttribute("titulo", "Listado de libros");
        return "listar";
    }

    @GetMapping("/listar-chunked")
    public String listarChunked(Model model) {
        Flux<Libro> libros = libroService.findAllNombreUpperCaseRepeat();
        model.addAttribute("libros", libros);
        model.addAttribute("titulo", "Listado de libros");
        return "listar-chunked";
    }
}
