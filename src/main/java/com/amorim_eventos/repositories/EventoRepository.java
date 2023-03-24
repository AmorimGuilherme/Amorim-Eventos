package com.amorim_eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amorim_eventos.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
