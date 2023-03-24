package com.amorim_eventos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amorim_eventos.entities.CasaDeShow;
import com.amorim_eventos.entities.Evento;
import com.amorim_eventos.services.CasaDeShowService;
import com.amorim_eventos.services.EventoService;

@Controller
@RequestMapping("/casa")
public class CasaDeShowController {

	@Autowired
	private CasaDeShowService casaDeShowService;

	@Autowired
	private EventoService eventoService;

	@GetMapping
	public ModelAndView editarCasaDeShow(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("casa/form.html");

		CasaDeShow casaDeShow;

		if (id == null) {
			casaDeShow = new CasaDeShow();
		} else {
			try {
				casaDeShow = casaDeShowService.obterCasaDeShow(id);

			} catch (Exception e) {
				casaDeShow = new CasaDeShow();
				mv.addObject("mensagem", e.getMessage());

			}

		}

		mv.addObject("casaDeShow", casaDeShow);
		mv.addObject("lista", casaDeShowService.listarCasaDeShow());
		return mv;

	}

	@PostMapping
	public ModelAndView salvarCasaDeShow(@Valid CasaDeShow casaDeShow, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("casa/form.html");

		boolean novo = true;

		if (casaDeShow.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("casaDeShow", casaDeShow);
			return mv;
		}

		CasaDeShow casaDeShowSalva = casaDeShowService.salvarCasaDeShow(casaDeShow);

		if (novo) {
			mv.addObject("casaDeShow", new CasaDeShow());
		} else {
			mv.addObject("casaDeShow", casaDeShowSalva);
		}

		mv.addObject("mensagem", "Casa de Show salva com sucesso!");
		mv.addObject("lista", casaDeShowService.listarCasaDeShow());

		return mv;
	}

	@GetMapping("/excluir")
	public ModelAndView exclumeirCasaDeShow(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/casa");

		List<Evento> eventos = eventoService.listarEvento();

		if (!eventos.isEmpty()) {
			redirectAttributes.addFlashAttribute("mensagemValida",
					"Não é possível excluir Casa de Show com evento cadastrado!");
			return mv;
		}

		try {
			casaDeShowService.excluirCasaDeShow(id);
			redirectAttributes.addFlashAttribute("mensagem", "Casa de Show excluída com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir Casa de Show!!" + e.getMessage());
		}

		return mv;
	}
}
