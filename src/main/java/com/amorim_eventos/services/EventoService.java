package com.amorim_eventos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amorim_eventos.entities.Evento;
import com.amorim_eventos.repositories.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	public Evento salvarEvento(Evento evento) {
		return eventoRepository.save(evento);
	}

	public List<Evento> listarEvento() {

		return eventoRepository.findAll();

	}

	public Evento obterEvento(Long id) throws Exception {

		Optional<Evento> evento = eventoRepository.findById(id);

		if (evento.isEmpty()) {
			throw new Exception("Evento não encontrado!");
		}

		return evento.get();
	}

	public void excluirEvento(Long id) {
		eventoRepository.deleteById(id);
	}

}
