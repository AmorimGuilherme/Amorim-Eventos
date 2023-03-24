package com.gft.gerenciador.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.gerenciador.entities.CasaDeShow;
import com.gft.gerenciador.entities.Evento;
import com.gft.gerenciador.entities.enums.GeneroMusical;
import com.gft.gerenciador.services.CasaDeShowService;
import com.gft.gerenciador.services.EventoService;

@Controller
@RequestMapping("popular")
public class PopularBancoController {

	@Autowired
	CasaDeShowService casaDeShowService;

	@Autowired
	EventoService eventoService;

	@GetMapping
	public ModelAndView popularBanco() {

		ModelAndView mv = new ModelAndView("redirect:/home");

		List<CasaDeShow> casas = casaDeShowService.listarCasaDeShow();

		CasaDeShow casa1 = null;
		CasaDeShow casa2 = null;
		CasaDeShow casa3 = null;

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dateFormat1 = null;
		Date dateFormat2 = null;
		Date dateFormat3 = null;

		try {
			dateFormat1 = formato.parse("23/12/2022");
			dateFormat2 = formato.parse("09/01/2023");
			dateFormat3 = formato.parse("01/07/2022");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (casas.isEmpty()) {

			casa1 = new CasaDeShow("Espaço das Americas", " R. Tagipuru, 795 - Barra Funda, São Paulo", null);
			casa2 = new CasaDeShow("Estadio Pacaembu", "Praça Charles Miller - Pacaembu, São Paulo ", null);
			casa3 = new CasaDeShow("Neo Quimica Arena", "Av. Miguel Ignácio Curi, 111 - Artur Alvim, São Paulo", null);

			casaDeShowService.salvarCasaDeShow(casa1);
			casaDeShowService.salvarCasaDeShow(casa2);
			casaDeShowService.salvarCasaDeShow(casa3);
		}

		Evento evento1 = null;
		Evento evento2 = null;
		Evento evento3 = null;

		List<Evento> eventos = eventoService.listarEvento();

		if (eventos.isEmpty()) {

			evento1 = new Evento("Show Racionais Mcs", 15000, dateFormat1, 150.0, casa1, GeneroMusical.RAP);
			evento2 = new Evento("Show Pericles", 32000, dateFormat2, 200.0, casa2, GeneroMusical.PAGODE);
			evento3 = new Evento("Show Chiclete Com Banana", 26000, dateFormat3, 78.0, casa3, GeneroMusical.AXE);

			eventoService.salvarEvento(evento1);
			eventoService.salvarEvento(evento2);
			eventoService.salvarEvento(evento3);

		}

		return mv;
	}

}
