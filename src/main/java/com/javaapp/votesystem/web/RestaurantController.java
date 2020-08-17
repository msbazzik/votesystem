package com.javaapp.votesystem.web;

import com.javaapp.votesystem.model.Restaurant;
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

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        checkNew(restaurant);
        LOG.info("create {} for user {}", restaurant, userId);
        return restaurantService.create(restaurant, userId);
    }

    @DeleteMapping("/{id}")
    //  @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete restaurant {} for user {}", id, userId);
        restaurantService.delete(id, userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    // @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(restaurant, id);
        LOG.info("update {} for user {}", restaurant, userId);
        restaurantService.update(restaurant, userId);
    }

    @GetMapping("/{date}/{id}")
    public RestaurantToWithMenu getByDate(@PathVariable int id, @PathVariable LocalDate date) {
        LOG.info("get restaurant with id {} by date {}", id, date);
        return RestaurantUtil.createToWithMenu(restaurantService.get(id, date));
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
}