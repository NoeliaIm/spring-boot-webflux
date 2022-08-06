package com.noeliaiglesias.springbootwebflux.app.controllers;

import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import com.noeliaiglesias.springbootwebflux.app.models.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
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

    @GetMapping("/editar/{id}")
    public Mono<String> editar(@PathVariable String id, Model model) {
        return this.libroService.findById(id).doOnNext(libro -> {
                    model.addAttribute("libro", libro);
                    model.addAttribute("titulo", "Editar libro");
                }).defaultIfEmpty(new Libro())
                .flatMap(libro -> {
                    if (libro.getId() == null) {
                        return Mono.error(new Exception("El libro no existe"));
                    } else {
                        return Mono.just(libro);
                    }
                })
                .then(Mono.just("editar")
                              .onErrorResume(e ->
                                                     Mono.just("redirect:/listado?error=el+libro+no+existe")));
    }

    @PostMapping("/crear")
    public Mono<String> crear(@Valid Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("libro", libro);
            model.addAttribute("titulo", "Error en el formulario");
            return Mono.just("crear");
        }
        return this.libroService.save(libro).thenReturn("redirect:/listado?success=libro+guardado+correctamente");
    }

    @GetMapping("/eliminar/{id}")
    public Mono<String> borrar(@PathVariable String id) {
        return this.libroService.findById(id)
                .defaultIfEmpty(new Libro())
                .flatMap(l -> {
                    if (l.getId() == null) {
                        Mono.error(new Exception("El libro no existe"));
                    }
                    return Mono.just(l);
                }).flatMap(l -> this.libroService.delete(l.getId()))
                .thenReturn("redirect:/listado?success=libro+borrado+correctamente")
                .onErrorResume(e ->
                                       Mono.just("redirect:/listado?error=el+libro+no+existe"));

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
