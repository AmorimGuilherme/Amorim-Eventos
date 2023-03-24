package com.amorim_eventos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amorim_eventos.entities.CasaDeShow;
import com.amorim_eventos.repositories.CasaDeShowRepository;

@Service
public class CasaDeShowService {

	@Autowired
	private CasaDeShowRepository casaDeShowRepository;

	public CasaDeShow salvarCasaDeShow(CasaDeShow casaDeShow) {
		return casaDeShowRepository.save(casaDeShow);
	}

	public List<CasaDeShow> listarCasaDeShow() {

		return casaDeShowRepository.findAll();

	}

	public CasaDeShow obterCasaDeShow(Long id) throws Exception {

		Optional<CasaDeShow> casaDeShow = casaDeShowRepository.findById(id);

		if (casaDeShow.isEmpty()) {
			throw new Exception("Casa de show não encontrada!");
		}

		return casaDeShow.get();
	}

	public void excluirCasaDeShow(Long id) {
		casaDeShowRepository.deleteById(id);
	}

}

