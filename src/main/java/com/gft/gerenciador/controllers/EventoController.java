package com.gft.gerenciador.controllers;

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

import com.gft.gerenciador.entities.CasaDeShow;
import com.gft.gerenciador.entities.Evento;
import com.gft.gerenciador.entities.enums.GeneroMusical;
import com.gft.gerenciador.services.CasaDeShowService;
import com.gft.gerenciador.services.EventoService;

@Controller
@RequestMapping("evento")
public class EventoController {

	@Autowired
	EventoService eventoService;

	@Autowired
	CasaDeShowService casaDeShowService;

	@GetMapping
	public ModelAndView editarEvento(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("evento/form.html");

		Evento evento;

		if (id == null) {
			evento = new Evento();
		} else {
			try {
				evento = eventoService.obterEvento(id);

			} catch (Exception e) {
				evento = new Evento();
				mv.addObject("mensagem", e.getMessage());
				mv.addObject("listarCasasDeShows", casaDeShowService.listarCasaDeShow());
				mv.addObject("listarGenerosMusicais", GeneroMusical.values());

			}

		}
		mv.addObject("evento", evento);
		mv.addObject("lista", eventoService.listarEvento());
		mv.addObject("listarCasasDeShows", casaDeShowService.listarCasaDeShow());
		mv.addObject("listarGenerosMusicais", GeneroMusical.values());

		return mv;

	}

	@PostMapping
	public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("evento/form.html");

		List<CasaDeShow> casas = casaDeShowService.listarCasaDeShow();
		mv.addObject("listarGenerosMusicais", GeneroMusical.values());

		if (casas.isEmpty()) {
			mv.addObject("mensagemErro", "Impossivel cadastrar um Evento sem Casa de Show cadastrada!!");
			return mv;

		}

		boolean novo = true;

		if (evento.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("evento", evento);
			return mv;
		}

		Evento eventoSalvo = eventoService.salvarEvento(evento);

		if (novo) {
			mv.addObject("evento", new Evento());
		} else {
			mv.addObject("evento", eventoSalvo);
		}

		mv.addObject("mensagem", "Evento salvo com sucesso!");
		mv.addObject("lista", eventoService.listarEvento());
		mv.addObject("listarCasasDeShows", casaDeShowService.listarCasaDeShow());

		return mv;
	}

	@GetMapping("/excluir")
	public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/evento");

		try {
			eventoService.excluirEvento(id);
			redirectAttributes.addFlashAttribute("mensagem", "Evento excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir Evento!!" + e.getMessage());
		}

		return mv;
	}
}
