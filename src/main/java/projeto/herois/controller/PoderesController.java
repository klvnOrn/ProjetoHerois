package projeto.herois.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projeto.herois.model.Poderes;
import projeto.herois.payload.ApiResponse;
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
	public Poderes getPoderById(@PathVariable("idPoder") UUID idPoder) throws NotFoundException {
			Poderes poder = this.cp.findById(idPoder).orElse(null);
			if (poder == null) {
				throw new NotFoundException();
			}
			return poder;
	}
	
	@PostMapping("/cadastrarPoder")
	public Poderes savePoder(@RequestBody Poderes poder) {
		return cp.save(poder);
	}
	
	@DeleteMapping("/deletarPoder/{idPoder}")
	public ResponseEntity<?> ResponseEntity (@PathVariable("idPoder") UUID idPoder) throws NotFoundException{
		Poderes poder = this.cp.findById(idPoder).orElse(null);
		if (poder == null) {
			throw new NotFoundException();
		}
		else {
			this.cp.deleteById(idPoder);
			return new ResponseEntity<Object>(new ApiResponse(true, "Poder deletado!"),new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@PutMapping("/atualizarPoder")
	public Poderes updatePoder(@RequestBody Poderes poder) {
		return cp.save(poder);
	}
}