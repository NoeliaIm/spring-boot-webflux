package com.noeliaiglesias.springbootwebflux.app.models.service;

import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LibroService {
    Flux<Libro> findAll();

    Flux<Libro> findAllNombreUpperCase();

    Flux<Libro> findAllNombreUpperCaseRepeat();

    Mono<Libro> findById(String id);

    Mono<Libro> save(Libro libro);

    Mono<Void> delete(String id);

    Mono<Libro> findByTitulo(String titulo);

    Mono<Libro> findByAutor(String autor);

    Mono<Libro> findByEditorial(String editorial);

    Mono<Libro> findByIsbn(String isbn);


}
