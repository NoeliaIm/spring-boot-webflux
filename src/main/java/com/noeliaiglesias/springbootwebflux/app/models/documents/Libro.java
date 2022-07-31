package com.noeliaiglesias.springbootwebflux.app.models.documents;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@ToString
@Document(collection = "libros")
public class Libro {
    @Id
    private String id;
    private String titulo;
    private String autor;
    private String editorial;
    private int numeroPaginas;
    private String isbn;
    private Date fechaPublicacion;
    private Double precio;
    private Date createdAt;
}
