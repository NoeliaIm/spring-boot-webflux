package com.noeliaiglesias.springbootwebflux.app.models.dao;

import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LibroDao extends ReactiveMongoRepository<Libro, String> {

    Mono<Libro> findByTitulo(String titulo);

    Mono<Libro> findByAutor(String autor);

    Mono<Libro> findByEditorial(String editorial);

    Mono<Libro> findByIsbn(String isbn);
}

