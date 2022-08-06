package com.noeliaiglesias.springbootwebflux.app.models.dao;

import com.noeliaiglesias.springbootwebflux.app.models.documents.Genero;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GeneroDao extends ReactiveMongoRepository<Genero, String> {

    Mono<Genero> findByNombre(String nombre);

    // Flux<Genero> findByLibro(String libroId);
}



