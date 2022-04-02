package moviecatalog.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import moviecatalog.models.Rating;
import moviecatalog.models.UserRatings;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackUserRating") /*
																 * , commandProperties = {
																 * 
																 * @HystrixProperty(name =
																 * "execution.isolation.thread.timeoutInMilliseconds",
																 * value = "10000"),
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
	public UserRatings getUserRating(String userId) {
		return restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratingsdata/user/" + userId, UserRatings.class);
	}

	public UserRatings getFallbackUserRating(String userId) {
		UserRatings userRating = new UserRatings();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating("0", 0)));
		return userRating;
	}

}
