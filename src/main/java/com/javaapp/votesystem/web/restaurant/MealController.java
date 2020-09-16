package com.javaapp.votesystem.web.restaurant;


import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.javaapp.votesystem.util.ValidationUtil.assureIdConsistent;
import static com.javaapp.votesystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {
    private static final Logger LOG = LoggerFactory.getLogger(MealController.class);

    static final String REST_URL = AdminRestaurantController.REST_URL + "/{restaurantId}/dishes";

    @Autowired
    private MealService mealService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@PathVariable int restaurantId, @Valid @RequestBody Meal meal) {
        checkNew(meal);
        LOG.info("create meal{} for restaurant {}", meal, restaurantId);
        Meal created = mealService.create(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Meal meal, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(meal, id);
        LOG.info("update meal {} for restaurant {}", meal, restaurantId);
        mealService.update(meal, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        LOG.info("delete meal {} for restaurant{}", id, restaurantId);
        mealService.delete(id, restaurantId);
    }
}
