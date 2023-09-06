package com.gama.gama.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.gama.entidades.Comentario;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long>{

    
}
