package com.gama.gama.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gama.gama.dto.PublicacionDTO;
import com.gama.gama.dto.PublicacionRespuesta;
import com.gama.gama.servicio.PublicacionServicio;
import com.gama.gama.utilerias.AppConstantes;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {
    @Autowired
    private PublicacionServicio publicacionServicio;

    @GetMapping
    public PublicacionRespuesta listarPublicaciones(
            @RequestParam(value="pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
            @RequestParam(value="pageSize",defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
            @RequestParam(value="sortBy",defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required=false) String ordenarPor,
            @RequestParam(value="sortDir",defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required=false) String sortDir
            ){
        return publicacionServicio.obtenerTodasLasPublicaciones(numeroDePagina,medidaDePagina,ordenarPor,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name="id") long id){
        return ResponseEntity.ok(publicacionServicio.obtenerPublicacionPorId(id));
    }

    @PostMapping
    private ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO),HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacionPorId(@Valid @RequestBody PublicacionDTO publicacionDTO, @PathVariable(name="id") long id){
        PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name="id") long id){
        publicacionServicio.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicacion eliminada con exito",HttpStatus.OK);
    }
}
