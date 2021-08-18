package projeto.herois.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projeto.herois.repository.CrudHerois;

@Controller
public class HeroisController {
	
	@Autowired
	private CrudHerois ch;
	
	@RequestMapping(value="/cadastrarHeroi", method=RequestMethod.GET)
	public String form() {
		return ;
	}
	
	@RequestMapping(value="/cadastrarHeroi", method=RequestMethod.POST)
	public String form() {
		return ;
}
