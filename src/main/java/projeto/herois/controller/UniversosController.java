package projeto.herois.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import projeto.herois.model.Universos;
import projeto.herois.repository.CrudUniversos;

public class UniversosController {
	@Autowired
	private CrudUniversos ur;
	
	@RequestMapping(value="/cadastrarUniversos", method=RequestMethod.GET)
	public String form() {
		return "universos/formUniversos";
	}
	
	@RequestMapping(value="/cadastrarUniversos", method=RequestMethod.POST)
	public String form(Universos universo) {
		ur.save(universo);
		return "redirect:/cadastrarUniversos";
	}
	
	@RequestMapping("/universos")
	public ModelAndView listaUniversos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Universos> universos = ur.findAll();
		mv.addObject("universos", universos);
		return mv;
	}
	
	@RequestMapping(value="/{idUniverso}", method=RequestMethod.GET)
	public ModelAndView detalhesUniversos(@PathVariable("idUniverso") UUID idUniverso){
		Universos universo = ur.findByIdUniverso(idUniverso);
		
	}
}
