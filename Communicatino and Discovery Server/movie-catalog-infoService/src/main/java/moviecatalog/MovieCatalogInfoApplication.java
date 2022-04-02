package moviecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogInfoApplication.class, args);
	}

}
