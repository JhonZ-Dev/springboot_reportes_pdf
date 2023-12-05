package com.example.preactica.controlador;

import com.example.preactica.model.Libros;
import com.example.preactica.servicio.Libro_servicio;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/libros")
public class LibrosControlador {

    @Autowired
    public Libro_servicio libroServicio;

    @PostMapping("/libros/{id_publicacion}")
    public Libros guardarConPublicaciones(@RequestBody Libros libros,
                                          @PathVariable Long id_publicacion){
        return libroServicio.guardarLibrosConPublicacion(libros,id_publicacion);
    }

    @GetMapping("/obtener")
    public List<Libros> listarTodosLibros(){
       return libroServicio.listarTodosLibros();
    }

    @PutMapping("/actualizar/{id_libro}")
    public Libros actualizar(@RequestBody Libros libros,
                             @PathVariable Long id_libro) {
        // Verificamos si está presente
        Libros existe = libroServicio.encontrarPorId(id_libro);
        if (existe != null) {
            // Actualizamos las propiedades del libro existente
            existe.setTitulo(libros.getTitulo());
            existe.setIsbn(libros.getIsbn());
            existe.setAnio_pub(libros.getAnio_pub());
            existe.setNum_pages(libros.getNum_pages());
            existe.setFecha_modificacion(existe.getFecha_modificacion());

            // Guardamos los cambios en el libro existente
            return libroServicio.guardarLibros(existe);
        } else {
            // Manejo de error o lanzar una excepción si el libro no existe
            // Puedes personalizar este comportamiento según tus necesidades
            return null; // O lanzar una excepción
        }
    }









    @GetMapping("/generar-pdf")
    public ResponseEntity<byte[]> generarInformePDF() {
        List<Libros> libros = libroServicio.listarTodosLibros();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            PdfPTable table = new PdfPTable(6); // 6 columnas para las propiedades de Libros
            addTableHeader(table);
            addRows(table, libros);

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "informe.pdf");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell("Título");
        table.addCell("Números de Páginas");
        table.addCell("ISBN");
        table.addCell("Fecha Creación");
        table.addCell("Fecha Modificación");
        table.addCell("Año de Publicación");
    }

    private void addRows(PdfPTable table, List<Libros> libros) {
        for (Libros libro : libros) {
            table.addCell(libro.getTitulo());
            table.addCell(String.valueOf(libro.getNum_pages()));
            table.addCell(libro.getIsbn());
            table.addCell(libro.getFecha_creacion().toString());
            table.addCell(libro.getFecha_modificacion().toString());
            table.addCell(String.valueOf(libro.getAnio_pub()));
        }
    }


}
