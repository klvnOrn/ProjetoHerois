package projeto.herois.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projeto.herois.model.Universos;
import projeto.herois.repository.CrudUniversos;

@RestController
public class UniversosController {
	
	@Autowired
	CrudUniversos cu;
	
	@GetMapping("/universos")
	public Iterable<Universos> getAllUniversos(){
		return cu.findAll();
		
	}
	
	@GetMapping("/universos/{idUniverso}")
	public Universos getUniversosById(@PathVariable("idUniverso") UUID idUniverso) {
		Universos universo = this.cu.findById(idUniverso).orElse(null);
		return universo;
	}
	
	@PostMapping("/cadastrarUniversos")
	public Universos saveUniversos(@RequestBody Universos universos) {
		return cu.save(universos);
	}
//	
//	@DeleteMapping("/deletarHeroi/{id}")
//	public void deletarHeroi(@PathVariable String id) {
//		ch.deleteById(id);
//	}
	
	@DeleteMapping("/deletarUniverso/{idUniverso}")
	public void deleteUniversosById(@PathVariable("idUniverso") UUID idUniverso) {
		this.cu.deleteById(idUniverso);
	}
}