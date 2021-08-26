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

import projeto.herois.model.Universos;
import projeto.herois.payload.ApiResponse;
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
	public Universos getUniversoById(@PathVariable("idUniverso") UUID idUniverso) throws NotFoundException {
		Universos universo = this.cu.findById(idUniverso).orElse(null);
		if (universo == null) {
			throw new NotFoundException();
		}
		return universo;
	}
	
	@PostMapping("/cadastrarUniverso")
	public Universos saveUniverso(@RequestBody Universos universo) {
		return cu.save(universo);
	}

	@DeleteMapping("/deletarUniverso/{idUniverso}")
	public ResponseEntity<?> ResponseEntity (@PathVariable("idUniverso") UUID idUniverso) throws NotFoundException{
		Universos universo = this.cu.findById(idUniverso).orElse(null); {
		if (universo == null) {
			throw new NotFoundException();
		}
		else {
			this.cu.deleteById(idUniverso);
			return new ResponseEntity<Object>(new ApiResponse(true, "Universo deletado!"),new HttpHeaders(), HttpStatus.OK);
			}
		}
	}
		
	@PutMapping("/atualizarUniverso")
	public ResponseEntity<?> updateUniverso(@RequestBody Universos universo) {
		try {
		getUniversoById(universo.getIdUniverso());
		} catch (NotFoundException e) {
		return new ResponseEntity<Object>(new ApiResponse(false, "Universo não encontrado!"),new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		cu.save(universo);
		return new ResponseEntity<Object>(new ApiResponse(true, "Universo atualizado!"),new HttpHeaders(), HttpStatus.OK);
		}
}