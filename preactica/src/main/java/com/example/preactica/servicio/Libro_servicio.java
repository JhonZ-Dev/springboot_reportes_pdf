package com.example.preactica.servicio;

import com.example.preactica.model.Libros;
import com.example.preactica.model.Publicacion;
import com.example.preactica.repositorio.Libros_Repo;
import com.example.preactica.repositorio.Publicaciones_libros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Libro_servicio {
    @Autowired
    public Libros_Repo librosRepo;
    @Autowired
    public Publicaciones_libros publicacionesLibros;

    //metodos crear
    public Libros guardarLibros(Libros libros){
        return librosRepo.save(libros);
    }
    //metodo para listar
    public List<Libros> listarTodosLibros(){
        return librosRepo.findAll();
    }
    //encontrar por id;

   Optional<Libros> buscarPorId(Long id_libro){
        return librosRepo.findById(id_libro);
   }
   //eliminar por id

    public void eliminarPorId(Long id_libro){
        librosRepo.deleteById(id_libro);
    }

    //metodod para guardar un libro dependiendo del id de la publicacion:

    public Libros guardarLibrosConPublicacion(Libros libros, Long id_publicacion){
        //recuperar lapublicacnion por id;
        Optional<Publicacion> optionalPublicacion = publicacionesLibros.findById(id_publicacion);

        //verificamos si la publicacion existe
        if (optionalPublicacion.isPresent()){
            //establecer la relacion en ambos lados(Libros como en publicaicones)
            Publicacion publicacion = optionalPublicacion.get();
            libros.setPublicacions(publicacion);
            publicacion.getLibros().add(libros);
            return librosRepo.save(libros);
        }else{
            return null;
        }
    }

    //buscar por id
    public Libros encontrarPorId(Long id_libro){
        return librosRepo.findById(id_libro).orElse(null);
    }
}
