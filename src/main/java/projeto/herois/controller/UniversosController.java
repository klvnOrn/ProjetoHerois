package projeto.herois.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/universo/{idUniverso}")
	public Universos getUniversoById(@PathVariable("idUniverso") UUID idUniverso) {
		Universos universo = this.cu.findById(idUniverso).orElse(null);
		return universo;
	}
	
	@PostMapping("/cadastrarUniverso")
	public Universos saveUniversos(@RequestBody Universos universo) {
		return cu.save(universo);
	}

	@DeleteMapping("/deletarUniverso/{idUniverso}")
	public void deleteUniversoById(@PathVariable("idUniverso") UUID idUniverso) {
		this.cu.deleteById(idUniverso);
	}
		
	@PutMapping("/atualizarUniverso")
	public Universos updateUniverso(@RequestBody Universos universo) {
		return cu.save(universo);
	}
	
}