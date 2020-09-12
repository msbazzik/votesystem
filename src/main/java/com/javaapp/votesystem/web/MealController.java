package com.javaapp.votesystem.web;


import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.service.MealService;
import com.javaapp.votesystem.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.javaapp.votesystem.util.ValidationUtil.assureIdConsistent;
import static com.javaapp.votesystem.util.ValidationUtil.checkNew;
import static com.javaapp.votesystem.web.RestaurantController.REST_URL;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    public static final String REST_URL = RestaurantController.REST_URL + "/{restaurantId}/dishes";

    @Autowired
    private MealService mealService;

    @PostMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@PathVariable int restaurantId, @RequestBody Meal meal) {
        int userId = SecurityUtil.authUserId();
        checkNew(meal);
        LOG.info("create meal{} for restaurant {} for user{}", meal, restaurantId, userId);
        //check role here
        Meal created = mealService.create(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restaurantId, @RequestBody Meal meal, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(meal, id);
        LOG.info("update {} for restaurant {} for user {}", meal, restaurantId, userId);
        //check role here
        mealService.update(meal, restaurantId);
    }

    @DeleteMapping(REST_URL + "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete meal {} for user {} for restaurant{}", id, userId, restaurantId);
        //check role here
        mealService.delete(id, restaurantId);
    }
}
