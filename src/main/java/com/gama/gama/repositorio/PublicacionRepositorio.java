package com.gama.gama.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.gama.entidades.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {
    
}
