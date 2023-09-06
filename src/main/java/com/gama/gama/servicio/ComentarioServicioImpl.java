package com.gama.gama.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.gama.dto.ComentarioDTO;
import com.gama.gama.entidades.Comentario;
import com.gama.gama.entidades.Publicacion;
import com.gama.gama.excepciones.ResourceNotFoundException;
import com.gama.gama.repositorio.PublicacionRepositorio;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    @Autowired
    private ComentarioServicioImpl comentarioRepositorio;

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = mapearEntidad(comentarioDTO);
        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id",publicacionId));
        
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepositorio.save(comentario);

        return mapearDTO(nuevoComentario);
    }

    private Comentario save(Comentario comentario) {
        return null;
    }

    private ComentarioDTO mapearDTO(Comentario comentario){
        ComentarioDTO comentarioDTO= new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setNombre(comentario.getNombre());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setCuerpo(comentario.getCuerpo());

        return comentarioDTO;
    }

    private Comentario mapearEntidad(ComentarioDTO comentarioDTO){
        Comentario comentario=new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());

        return comentario;
    }
    
}
