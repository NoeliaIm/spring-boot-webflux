package com.noeliaiglesias.springbootwebflux.app;

import com.github.javafaker.Faker;
import com.noeliaiglesias.springbootwebflux.app.models.dao.GeneroDao;
import com.noeliaiglesias.springbootwebflux.app.models.dao.LibroDao;
import com.noeliaiglesias.springbootwebflux.app.models.documents.Genero;
import com.noeliaiglesias.springbootwebflux.app.models.documents.Libro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class LibroInitializer implements CommandLineRunner {
    private final LibroDao libroDao;

    private final GeneroDao generoDao;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public LibroInitializer(LibroDao libroDao, GeneroDao generoDao, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.libroDao = libroDao;
        this.generoDao = generoDao;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public void run(String... args) {
        log.info("Limpiando colección libros");
        reactiveMongoTemplate.dropCollection(Libro.class).subscribe();
        log.info("Libros borrados");

        log.info("Inicializando libros...");
        Faker faker = new Faker();
        List<Libro> librosList = new ArrayList<>();
        List<Genero> generoList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Genero genero = new Genero();
            genero.setId(String.valueOf(i));
            genero.setNombre(faker.book().genre());
            generoList.add(genero);
        }
        Flux.fromIterable(generoList).flatMap(genero -> generoDao.save(genero)).subscribe(g -> log.info("Género guardado: " + g.toString()));
        log.info("Géneros inicializados...");

        for (int i = 0; i < 10; i++) {
            Libro libro = new Libro();
            libro.setTitulo(faker.book().title());
            libro.setAutor(faker.book().author());
            libro.setEditorial(faker.book().publisher());
            libro.setNumeroPaginas(faker.number().numberBetween(50, 500));
            libro.setIsbn(faker.code().isbn10());
            libro.setFechaPublicacion(faker.date().birthday());
            libro.setPrecio(faker.number().randomDouble(2, 0, 30));
            libro.setGenero(generoList.get(i % 3));
            librosList.add(libro);
        }
        Flux.fromIterable(librosList).flatMap(libro -> {
            libro.setCreatedAt(new Date());
            return libroDao.save(libro);
        }).subscribe(l -> log.info("Libro guardado: " + l.toString()));
        log.info("Libros inicializados...");

    }
}

