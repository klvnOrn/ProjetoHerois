package projeto.herois.repository;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;


@Component
public interface FileHelper {

    void init();

    String store(MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String filename);


}