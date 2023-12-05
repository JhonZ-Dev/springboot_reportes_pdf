package com.example.preactica.repositorio;

import com.example.preactica.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Libros_Repo extends JpaRepository<Libros, Long> {

}
