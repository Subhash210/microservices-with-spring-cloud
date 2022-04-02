package moviecatalog.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moviecatalog.models.Rating;
import moviecatalog.models.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("user/{userId}")
	public UserRatings getUserRating(@PathVariable("userId") String userId) {
		// List<Rating> ratings = Arrays.asList(new Rating("m1", 3), new Rating("m2",
		// 4));

		UserRatings userRating = new UserRatings();
		userRating.initData(userId);
		return userRating;
	}

}
