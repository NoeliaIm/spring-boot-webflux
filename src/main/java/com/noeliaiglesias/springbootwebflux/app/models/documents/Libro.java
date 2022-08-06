package com.noeliaiglesias.springbootwebflux.app.models.documents;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@ToString
@Document(collection = "libros")
public class Libro {
    @Id
    private String id;
    @NotEmpty
    private String titulo;
    @NotEmpty
    private String autor;
    private String editorial;
    private int numeroPaginas;
    private String isbn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;
    @NotNull
    private Double precio;
    private Genero genero;
    private Date createdAt;
}
