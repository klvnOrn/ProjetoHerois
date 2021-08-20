package projeto.herois.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projeto.herois.model.Herois;
import projeto.herois.repository.CrudHerois;

@RestController
public class HeroisController {
	
	@Autowired
	CrudHerois ch;
	
	@GetMapping("/herois")
	public Iterable<Herois> getAllHerois(){
		return ch.findAll();
		
	}
	
	@GetMapping("/herois/{id}")
	public Herois getHeroisById(@PathVariable String idHeroi) {
		return ch.findById(idHeroi).get();
	}
	
	@PostMapping("/cadastrarHerois")
	public Herois saveHerois(@RequestBody Herois herois) {
		return ch.save(herois);
	}
	
	@DeleteMapping("/deletarHeroi/{id}")
	public void deletarHeroi(@PathVariable String id) {
		ch.deleteById(id);
	}
}