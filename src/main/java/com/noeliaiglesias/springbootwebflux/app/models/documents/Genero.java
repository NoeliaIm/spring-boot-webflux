package com.noeliaiglesias.springbootwebflux.app.models.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "generos")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Genero {

    @Id
    private String id;

    @NotEmpty
    private String nombre;

    public Genero(String nombre) {
        this.nombre = nombre;
    }


}
