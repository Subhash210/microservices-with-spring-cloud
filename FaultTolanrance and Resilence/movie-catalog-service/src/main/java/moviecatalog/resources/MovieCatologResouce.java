package moviecatalog.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import moviecatalog.models.CatalogItem;
import moviecatalog.models.UserRatings;
import moviecatalog.services.MovieInfo;
import moviecatalog.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatologResouce {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingInfo userRatingInfo;

	/*
	 * @Autowired private WebClient.Builder builder;
	 */

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRatings userRating = userRatingInfo.getUserRating(userId);

		return userRating.getUserRatings().stream().map(rating -> movieInfo.getCataloItem(rating))
				.collect(Collectors.toList());
		// USING WEBCLIENT
		/*
		 * Movie movie = builder.build().get().uri("http://localhost:8082/movies/" +
		 * rating.getMovieId()).retrieve() .bodyToMono(Movie.class).block();
		 */
	}

	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
		return Arrays.asList(new CatalogItem("No movie", "", 0));
	}

}
