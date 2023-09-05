package com.gama.gama.servicio;

import com.gama.gama.dto.PublicacionDTO;
import com.gama.gama.dto.PublicacionRespuesta;

public interface PublicacionServicio {
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

    public PublicacionDTO obtenerPublicacionPorId(long id);

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);

    public void eliminarPublicacion(long id);
}
