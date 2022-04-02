package moviecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
public class MovieCatalogServiceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/*
	 * @Bean
	 *
	 * @LoadBalanced public RestTemplate getRestTemplate() {
	 * HttpComponentsClientHttpRequestFactory clientRequestFactor = new
	 * HttpComponentsClientHttpRequestFactory();
	 * clientRequestFactor.setConnectTimeout(5000); return new
	 * RestTemplate(clientRequestFactor); }
	 */

	@Bean
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
