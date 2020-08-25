package com.javaapp.votesystem.web;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.service.MealService;
import com.javaapp.votesystem.service.RestaurantService;
import com.javaapp.votesystem.service.VoteService;
import com.javaapp.votesystem.to.RestaurantToWithMenu;
import com.javaapp.votesystem.to.RestaurantToWithVote;
import com.javaapp.votesystem.util.RestaurantUtil;
import com.javaapp.votesystem.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.javaapp.votesystem.util.ValidationUtil.assureIdConsistent;
import static com.javaapp.votesystem.util.ValidationUtil.checkNew;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MealService mealService;

    @Autowired
    private VoteService voteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        checkNew(restaurant);
        LOG.info("create {} for user {}", restaurant, userId);
        //check role here
        return restaurantService.create(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    //  @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete restaurant {} for user {}", restaurantId, userId);
        //check role here
        restaurantService.delete(restaurantId);
    }

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    // @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(restaurant, restaurantId);
        LOG.info("update {} for user {}", restaurant, userId);
        //check role here
        restaurantService.update(restaurant);
    }

    @GetMapping("/{date}/{restaurantId}")
    public RestaurantToWithMenu getByDate(@PathVariable int restaurantId, @PathVariable LocalDate date) {
        LOG.info("get restaurant with id {} by date {}", restaurantId, date);
        return RestaurantUtil.createToWithMenu(restaurantService.getByDate(restaurantId, date));
    }

    @GetMapping("/{date}")
    public List<RestaurantToWithMenu> getAllWithMenuByDate(@PathVariable LocalDate date) {
        LOG.info("get all restaurants with menu by date {}", date);
        return RestaurantUtil.getRestaurantsToWithMenu(restaurantService.getAllByDate(date));
    }

    @GetMapping("/votes/{date}")
    public List<RestaurantToWithVote> getAllWithVotesByDate(@PathVariable LocalDate date) {
        LOG.info("get all restaurants with votes by date {}", date);
        return RestaurantUtil.getRestaurantsToWithVoteCount(restaurantService.getAllByDate(date),
                voteService.getAllByDate(date));
    }

    @PostMapping(value = "/{restaurantId}/meal", produces = APPLICATION_JSON_VALUE)
    public Meal createMeal(@PathVariable int restaurantId, @RequestBody Meal meal) {
        int userId = SecurityUtil.authUserId();
        checkNew(meal);
        LOG.info("create meal{} for restaurant {} for user{}", meal, restaurantId, userId);
        //check role here
        return mealService.createMeal(meal, restaurantId);
    }

    @PutMapping(value = "/{restaurantId}/meal/{mealId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    // @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMeal(@PathVariable int restaurantId, @RequestBody Meal meal, @PathVariable int mealId) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(meal, mealId);
        LOG.info("update {} for restaurant {} for user {}", meal, restaurantId, userId);
        //check role here
        mealService.updateMeal(meal, restaurantId);
    }

    @DeleteMapping("/{restaurantId}/{mealId}")
    // @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable int restaurantId, @PathVariable int mealId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete meal {} for user {} for restaurant{}", mealId, userId, restaurantId);
        //check role here
        mealService.deleteMeal(mealId, restaurantId);
    }
}