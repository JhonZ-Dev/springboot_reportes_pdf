package com.example.preactica.controlador;

import com.example.preactica.model.Publicacion;
import com.example.preactica.servicio.Publicacion_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
@CrossOrigin(value = "http://localhost:4200")
public class PublicacionesControlador {

    @Autowired
    private Publicacion_servicio publicacionServicio;



    @PostMapping("/guardar/publicaciones")
    public Publicacion guardar(@RequestBody Publicacion publicacion){
        return publicacionServicio.guardarPublicacion(publicacion);
    }

    @GetMapping("/listar/publicaciones")
    public List<Publicacion> listar(){
        return publicacionServicio.listarPublicaciones();
    }

    @PutMapping("/actualizar/{id_publicacion}")
    public Publicacion guardar(@RequestBody Publicacion publicacion,
                               @PathVariable Long id_publicacion){
        //verificamos si existe
        Publicacion existe = publicacionServicio.buscarPorId(id_publicacion);
        if(existe !=null){
            //actualizamos
            existe.setNombre(publicacion.getNombre());
            return publicacionServicio.guardarPublicacion(existe);
        }else{
            // Manejo de error o lanzar una excepción si el libro no existe
            // Puedes personalizar este comportamiento según tus necesidades
            return null; // O lanzar una excepción
        }
    }
}
