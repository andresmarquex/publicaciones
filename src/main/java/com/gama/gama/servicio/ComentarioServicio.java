package com.gama.gama.servicio;

import com.gama.gama.dto.ComentarioDTO;

public interface ComentarioServicio {
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
}
