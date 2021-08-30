package projeto.herois.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import projeto.herois.model.Avatar;
import projeto.herois.payload.ApiResponse;
import projeto.herois.repository.CrudAvatar;
import projeto.herois.repository.FileHelper;

@RestController
public class AvatarController {
	@Autowired
	private FileHelper fileDownloadHelper;
	
	@Autowired
	private CrudAvatar ca;
	
	private FileHelper fileHelper;
	
	public AvatarController(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }
	
	@GetMapping("/avatares")
	public Iterable<Avatar> getAllAvatares(){
		return ca.findAll();
	}
	
	@GetMapping("/avatar/{idAvatar}")
	public Avatar getAvatarById(@PathVariable("idAvatar") UUID idAvatar) throws NotFoundException {
			Avatar avatar = this.ca.findById(idAvatar).orElse(null);
			if (avatar == null) {
				throw new NotFoundException();
			}
			return avatar;
	}
	
    @GetMapping("/downloadAvatar/{nomeAvatar}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String nomeAvatar) {
    	String filename = nomeAvatar;
    	System.out.println(filename);
        Resource resource = fileDownloadHelper.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
    @PostMapping("/cadastrarAvatar")
    @ResponseBody
    public Avatar uploadFile(@RequestParam("file") MultipartFile file, Avatar avatar) throws IOException {
    	fileHelper.store(file);
        Path rootLocation = Paths.get("./uploads");
        String filePath = rootLocation.resolve(StringUtils.cleanPath(file.getOriginalFilename())).toString();
        avatar.setPathAvatar(filePath);
        avatar.setNomeAvatar(file.getOriginalFilename());
        ca.save(avatar);
		return avatar;
    }
    
    @DeleteMapping("/deletarAvatar/{idAvatar}")
	public ResponseEntity<?> ResponseEntity (@PathVariable("idAvatar") UUID idAvatar) throws NotFoundException{
		Avatar avatar = this.ca.findById(idAvatar).orElse(null);
		if (avatar == null) {
			throw new NotFoundException();
		}
		else {
			this.ca.deleteById(idAvatar);
			return new ResponseEntity<Object>(new ApiResponse(true, "Avatar deletado!"),new HttpHeaders(), HttpStatus.OK);
		}
	}
    
//    @PutMapping("/atualizarAvatar/{idAvatar}")
//	public ResponseEntity<?> updateAvatar(@RequestParam("file") MultipartFile file, Avatar avatar) throws NotFoundException, IOException{
//    	Avatar avatarFile = new Avatar();
//    	avatarFile = uploadFile(file, avatarFile);
//		getAvatarById(avatar.getIdAvatar());
//		ca.save(avatar);
//		return new ResponseEntity<Object>(new ApiResponse(true, "Avatar atualizado!"),new HttpHeaders(), HttpStatus.OK);
//	}
}
