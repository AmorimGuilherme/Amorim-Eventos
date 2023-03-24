package com.gft.gerenciador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.gerenciador.services.EventoService;

@Controller
@RequestMapping("home")
public class HomeController {

	@Autowired
	EventoService eventoService;

	@GetMapping()
	public ModelAndView listarEventos() {

		ModelAndView mv = new ModelAndView("home/form.html");

		mv.addObject("lista", eventoService.listarEvento());

		return mv;
	}

}
