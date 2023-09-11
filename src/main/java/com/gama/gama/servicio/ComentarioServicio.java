package com.gama.gama.servicio;

import java.util.List;

import com.gama.gama.dto.ComentarioDTO;

public interface ComentarioServicio {
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);

    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long PublicacionId);

    public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId);

    public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudCOmentario);

    public void eliminarComentario(Long publicacionId, Long comentarioId);
}
