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

}
