package projeto.herois.controller;

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
	public Herois getHeroisById(@PathVariable String id) {
		return ch.findById(id).get();
	}
	
	@PostMapping("/herois")
	public Herois saveHerois(@RequestBody Herois herois) {
		return ch.save(herois);
	}
	
	@DeleteMapping("/herois/{id}")
	public void deleteHeroi(@PathVariable String id) {
		ch.deleteById(id);
	}
	
}