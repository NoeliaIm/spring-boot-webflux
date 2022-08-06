package com.noeliaiglesias.springbootwebflux.app.models.service.impl;

import com.noeliaiglesias.springbootwebflux.app.models.dao.GeneroDao;
import com.noeliaiglesias.springbootwebflux.app.models.documents.Genero;
import com.noeliaiglesias.springbootwebflux.app.models.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GeneroServiceImpl implements GeneroService {

    private GeneroDao generoDao;

    @Autowired
    public GeneroServiceImpl(GeneroDao generoDao) {
        this.generoDao = generoDao;
    }

    @Override
    public Mono<Genero> findById(String id) {
        return this.generoDao.findById(id);
    }

    @Override
    public Flux<Genero> findAll() {
        return this.generoDao.findAll();
    }

    @Override
    public Mono<Genero> findByNombre(String nombre) {
        return this.generoDao.findByNombre(nombre);
    }

    @Override
    public Mono<Genero> save(Genero genero) {
        return this.generoDao.save(genero);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.generoDao.deleteById(id);
    }

    @Override
    public Flux<Genero> findByLibroId(String libroId) {
        //  return this.generoDao.findByLibroId(libroId);
        return null;
    }
}
