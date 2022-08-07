package com.noeliaiglesias.springbootwebflux.app.models.service.impl;

import com.noeliaiglesias.springbootwebflux.app.models.dao.LibroDao;
import com.noeliaiglesias.springbootwebflux.app.models.documents.Genero;
import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import com.noeliaiglesias.springbootwebflux.app.models.service.GeneroService;
import com.noeliaiglesias.springbootwebflux.app.models.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroDao libroDao;

    private final GeneroService generoService;

    @Autowired
    public LibroServiceImpl(LibroDao libroDao, GeneroService generoService) {
        this.libroDao = libroDao;
        this.generoService = generoService;
    }

    @Override
    public Flux<Libro> findAll() {
        return libroDao.findAll();
    }

    @Override
    public Flux<Genero> findAllGeneros() {
        return generoService.findAll();
    }

    @Override
    public Flux<Libro> findByGeneroId(String generoId) {
        return null;
    }

    @Override
    public Flux<Libro> findAllNombreUpperCase() {
        return this.libroDao.findAll().map(libro -> {
            libro.setTitulo(libro.getTitulo().toUpperCase());
            return libro;
        });
    }

    @Override
    public Flux<Libro> findAllNombreUpperCaseRepeat() {
        return this.libroDao.findAll().map(libro -> {
            libro.setTitulo(libro.getTitulo().toUpperCase());
            return libro;
        }).repeat(5000);
    }


    @Override
    public Mono<Libro> findById(String id) {
        return libroDao.findById(id);
    }

    @Override
    public Mono<Libro> save(Libro libro) {
        libro.setCreatedAt(new Date());
        return libroDao.save(libro);
    }

    @Override
    public Mono<Void> delete(String id) {
        return libroDao.deleteById(id);
    }

    @Override
    public Mono<Libro> findByTitulo(String titulo) {
        return libroDao.findByTitulo(titulo);
    }

    @Override
    public Mono<Libro> findByAutor(String autor) {
        return libroDao.findByAutor(autor);
    }

    @Override
    public Mono<Libro> findByEditorial(String editorial) {
        return libroDao.findByEditorial(editorial);
    }

    @Override
    public Mono<Libro> findByIsbn(String isbn) {
        return libroDao.findByIsbn(isbn);
    }

}
