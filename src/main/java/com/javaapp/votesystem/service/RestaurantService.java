package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.javaapp.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant, userId);
    }

    public void delete(int restaurantId, int userId) {
        checkNotFoundWithId(restaurantRepository.delete(restaurantId, userId), restaurantId);
    }

    public void update(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant, userId), restaurant.getId());
    }

    public Restaurant getByDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return checkNotFoundWithId(restaurantRepository.getByDate(restaurantId, date), restaurantId);
    }

    public List<Restaurant> getAllByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return restaurantRepository.getAll(date);
    }

    public List<Meal> createMenu(Integer restaurantId, int userId, LocalDate date, List<Meal> meals) {
        Assert.notNull(meals, "meals must not be null");
        Assert.notNull(restaurantId, "restaurantId must not be null");
        Assert.notNull(date, "date must not be null");
        return restaurantRepository.createMenu(restaurantId, userId, date, meals);
    }

    public void deleteMenu(int restaurantId, int userId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        checkNotFoundWithId(restaurantRepository.deleteMenu(restaurantId, userId, date), restaurantId);
    }
}