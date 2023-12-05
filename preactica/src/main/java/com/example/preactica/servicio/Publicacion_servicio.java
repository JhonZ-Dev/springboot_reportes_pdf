package com.example.preactica.servicio;

import com.example.preactica.model.Publicacion;
import com.example.preactica.repositorio.Publicaciones_libros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Publicacion_servicio {
    @Autowired
    private Publicaciones_libros publicacionesLibros;

    public Publicacion guardarPublicacion(Publicacion publicacion){
        return publicacionesLibros.save(publicacion);
    }

    public Optional<Publicacion> encontrarPorId(Long id_publicacion){
        return publicacionesLibros.findById(id_publicacion);
    }
    public List<Publicacion> listarPublicaciones(){
        return publicacionesLibros.findAll();
    }

    public Publicacion buscarPorId(Long id_publicacion){
        return publicacionesLibros.findById(id_publicacion).orElse(null);
    }
}
