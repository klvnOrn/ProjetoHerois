package projeto.herois.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import projeto.herois.model.Avatar;
import projeto.herois.repository.CrudAvatar;

@RestController
public class AvatarController {
	
	@Autowired
	CrudAvatar ca;
	
	@GetMapping("/avatar/{idAvatar}")
	public byte[] getImagemById(@PathVariable("idAvatar") UUID idAvatar) throws NotFoundException{
		Avatar avatar = this.ca.findById(idAvatar).orElse(null);
		if (avatar == null) {
			throw new NotFoundException();
		}
		return avatar.getAvatar();
	}
	
	@PostMapping("/cadastrarAvatar")
	public Avatar saveImage(@ModelAttribute Avatar avatar, @RequestParam MultipartFile file) {
		try {
			avatar.setAvatar(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ca.save(avatar);
	}
}
