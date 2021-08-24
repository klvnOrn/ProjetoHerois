package projeto.herois.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projeto.herois.model.Herois;
import projeto.herois.model.Poderes;
import projeto.herois.repository.CrudHerois;

@RestController
public class HeroisController {
	
	@Autowired
	CrudHerois ch;
	
	@GetMapping("/herois")
	public Iterable<Herois> getAllHerois(){
		return ch.findAll();
		
	}
	
	@GetMapping("/heroi/{idHeroi}")
	public Herois getHeroiById(@PathVariable("idHeroi") UUID idHeroi) {
		Herois heroi = this.ch.findById(idHeroi).orElse(null);
		return heroi;
	}
	
	@PostMapping("/cadastrarHeroi")
	public Herois saveHeroi(@RequestBody Herois heroi) {
		System.out.println(heroi.getNomeHeroi());
		if(heroi.getNomeHeroi().equals("Thanos")) {
			System.out.println("Coletando as joiais do infinito...");
//			ch.thanosDelete(getAllHerois());
		}
		return ch.save(heroi);
	}

	@DeleteMapping("/deletarHeroi/{idHeroi}")
	public void deleteHeroiById(@PathVariable("idHeroi") UUID idHeroi) {
		this.ch.deleteById(idHeroi);
	}
	
	@PutMapping("/atualizarHeroi")
	public Herois updateHeroi(@RequestBody Herois heroi) {
//		Registro antigo
		Herois heroiVelho = getHeroiById(heroi.getIdHeroi());
//		Verificar se a data de cadastro no request é null
		if(heroi.getDataCadastro() == null) {
			System.out.println("dataCadastro is null");
//			Coloca a data do registro antigo no novo request
			heroi.setDataCadastro(heroiVelho.getDataCadastro());
		}
//		Verificar se o universo no request é null
		if(heroi.getUniverso() == null) {
			System.out.println("universo is null");
//			Coloca o universo antigo no novo request
			heroi.setUniverso(heroiVelho.getUniverso());
		}

//		Verificar se o poder no request é null
		if(heroi.getPoder() == null) {
			System.out.println("poder is null");
//			Coloca o poder antigo no novo request
			heroi.setPoder(heroiVelho.getPoder());
		}
		else {
//			Adicionar novo poder a lista
			List<Poderes> a = heroi.getPoder();
			a.addAll(heroiVelho.getPoder());
			heroi.setPoder(a);
		}
		return ch.save(heroi);
	}
}