package projeto.herois.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projeto.herois.model.Poderes;
import projeto.herois.repository.CrudPoderes;

@RestController
public class PoderesController {
	
	@Autowired
	CrudPoderes cp;
	
	@GetMapping("/poderes")
	public Iterable<Poderes> getAllPoderes(){
		return cp.findAll();
		
	}
	
	@GetMapping("/poder/{idPoder}")
	public Poderes getPoderById(@PathVariable("idPoder") UUID idPoder) {
		Poderes poder = this.cp.findById(idPoder).orElse(null);
		return poder;
	}
	
	@PostMapping("/cadastrarPoder")
	public Poderes savePoder(@RequestBody Poderes poder) {
		return cp.save(poder);
	}
	
	@DeleteMapping("/deletarPoder/{idPoder}")
	public void deletePoderById(@PathVariable("idPoder") UUID idPoder) {
		this.cp.deleteById(idPoder);
	}
}