package com.noeliaiglesias.springbootwebflux.app.models.service;

import com.noeliaiglesias.springbootwebflux.app.models.documents.Genero;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeneroService {
    Mono<Genero> findById(String id);

    Flux<Genero> findAll();

    Mono<Genero> findByNombre(String nombre);

    Mono<Genero> save(Genero genero);

    Mono<Void> delete(String id);

    Flux<Genero> findByLibroId(String libroId);

}
