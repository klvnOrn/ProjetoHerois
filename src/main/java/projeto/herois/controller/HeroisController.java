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
	
	@GetMapping("/herois/{idHeroi}")
	public Herois getHeroisById(@PathVariable("idHeroi") UUID idHeroi) {
		Herois heroi = this.ch.findById(idHeroi).orElse(null);
		return heroi;
	}
	
	@PostMapping("/cadastrarHeroi")
	public Herois saveHerois(@RequestBody Herois heroi) {
		return ch.save(heroi);
	}
	
	@DeleteMapping("/deletarHeroi/{idHeroi}")
	public void deleteHeroiById(@PathVariable("idHeroi") UUID idHeroi) {
		this.ch.deleteById(idHeroi);
	}
}