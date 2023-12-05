package com.example.preactica.repositorio;

import com.example.preactica.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Publicaciones_libros extends JpaRepository<Publicacion, Long> {

}
