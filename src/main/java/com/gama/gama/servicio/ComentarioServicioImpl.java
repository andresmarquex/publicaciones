package com.gama.gama.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gama.gama.dto.ComentarioDTO;
import com.gama.gama.entidades.Comentario;
import com.gama.gama.entidades.Publicacion;
import com.gama.gama.excepciones.BlogAppException;
import com.gama.gama.excepciones.ResourceNotFoundException;
import com.gama.gama.repositorio.ComentarioRepositorio;
import com.gama.gama.repositorio.PublicacionRepositorio;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

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

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long PublicacionId){
        List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(PublicacionId);
        return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(Long publicacionId,Long comentarioId){
        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id",publicacionId));
        Comentario comentario=comentarioRepositorio.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
        
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        return mapearDTO(comentario);
    }

    @Override
    public ComentarioDTO actualizarComentario( Long publicacionId, Long comentarioId, ComentarioDTO solicitudComentario){
        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id",publicacionId));
        Comentario comentario=comentarioRepositorio.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
        
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        comentario.setNombre(solicitudComentario.getNombre());
        comentario.setEmail(solicitudComentario.getEmail());
        comentario.setCuerpo(solicitudComentario.getCuerpo());

        Comentario comentarioActualizado = comentarioRepositorio.save(comentario);
        return mapearDTO(comentarioActualizado);
    }

    @Override
    public void eliminarComentario(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id",publicacionId));
        Comentario comentario=comentarioRepositorio.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
        
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        comentarioRepositorio.delete(comentario);
    }

     private ComentarioDTO mapearDTO(Comentario comentario){
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;
    }

    private Comentario mapearEntidad(ComentarioDTO comentarioDTO){
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
        return comentario;
    }
    
}
