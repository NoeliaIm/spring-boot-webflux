package com.noeliaiglesias.springbootwebflux.app.controllers;

import com.noeliaiglesias.springbootwebflux.app.models.dao.LibroDao;
import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/libros")
public class LibroRestController {

    private LibroDao libroDao;

    @Autowired
    public LibroRestController(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @GetMapping()
    public Flux<Libro> listar() {
        return libroDao.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Libro> listar(@PathVariable String id) {
        return libroDao.findById(id);
    }
}
