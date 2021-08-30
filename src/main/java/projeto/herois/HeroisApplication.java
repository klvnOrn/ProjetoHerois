package projeto.herois;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import projeto.herois.storage.StorageProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HeroisApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroisApplication.class, args);
	}

}
