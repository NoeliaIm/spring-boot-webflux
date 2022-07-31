package com.noeliaiglesias.springbootwebflux.app.models.dao;

import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LibroDao extends ReactiveMongoRepository<Libro, String> {
   /* Mono<Libro> findById(String id);
    Mono save(Libro libro);
    void delete(String id);
    Mono<Libro> findByTitulo(String titulo);
    Mono<Libro> findByAutor(String autor);
    Mono<Libro> findByEditorial(String editorial);
    Mono<Libro> findByIsbn (String isbn);
    Flux<Libro> findAll();*/
}

