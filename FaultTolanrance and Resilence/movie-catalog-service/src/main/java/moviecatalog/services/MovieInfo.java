package moviecatalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import moviecatalog.models.CatalogItem;
import moviecatalog.models.Movie;
import moviecatalog.models.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem") /*
																 * commandProperties = {
																 * 
																 * @HystrixProperty(name =
																 * "execution.isolation.thread.timeoutInMilliseconds",
																 * value = "2000"),
																 * 
																 * @HystrixProperty(name =
																 * "circuitBreaker.requestVolumeThreshold", value =
																 * "6"),
																 * 
																 * @HystrixProperty(name =
																 * "circuitBreaker.errorThresholdPercentage", value =
																 * "50"),
																 * 
																 * @HystrixProperty(name =
																 * "circuitBreaker.sleepWindowInMilliseconds", value =
																 * "10000") })
																 */
	public CatalogItem getCataloItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICES/movies/" + rating.getMovieId(),
				Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());

	}

}
