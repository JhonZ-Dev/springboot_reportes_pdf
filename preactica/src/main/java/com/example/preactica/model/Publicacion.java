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
@Table(name = "tbl_publicaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publicacion;
    private String nombre;

    @CreationTimestamp
    private LocalDateTime fecha_creacion;

    @UpdateTimestamp
    private LocalDateTime fecha_modificacion;


    @JsonIgnore
    @OneToMany(mappedBy = "publicacions",cascade = CascadeType.ALL)
    private List<Libros> libros;

    @Override
    public String toString() {
        return "Publicacion{" +
                "nombre='" + nombre + '\'' +
                ", fecha_publicacion=" + fecha_creacion +
                // Otras propiedades...
                '}';
    }

}
