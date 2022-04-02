package moviecatalog.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import moviecatalog.models.CatalogItem;
import moviecatalog.models.Movie;
import moviecatalog.models.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatologResouce {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * @Autowired private WebClient.Builder builder;
	 */

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRatings userRating = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratingsdata/user/" + userId,
				UserRatings.class);

		return userRating.getUserRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICES/movies/" + rating.getMovieId(),
					Movie.class);

			// USING WEBCLIENT
			/*
			 * Movie movie = builder.build().get().uri("http://localhost:8082/movies/" +
			 * rating.getMovieId()).retrieve() .bodyToMono(Movie.class).block();
			 */

			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		}).collect(Collectors.toList());

	}

}
