package com.example.preactica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name="tbl_books")
@AllArgsConstructor
@NoArgsConstructor

public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_libro;
    private String isbn;
    private String titulo;
    private Integer num_pages;
    private String anio_pub;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="id_publicacion", referencedColumnName = "id_publicacion")
    private Publicacion publicacions;

    @CreationTimestamp
    private LocalDateTime fecha_creacion;

    @UpdateTimestamp
    private LocalDateTime fecha_modificacion;

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", num_pages=" + num_pages +
                ", isbn='" + isbn + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", fecha_modificacion=" + fecha_modificacion +
                ", anio_pub=" + anio_pub +
                '}';
    }


}
