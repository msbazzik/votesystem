package com.javaapp.votesystem.service;

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

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    public void delete(int restaurantId) {
        checkNotFoundWithId(restaurantRepository.delete(restaurantId), restaurantId);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    public Restaurant get(int restaurantId) {
        return checkNotFoundWithId(restaurantRepository.get(restaurantId), restaurantId);
    }

    public Restaurant getByDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return checkNotFoundWithId(restaurantRepository.getByDate(restaurantId, date), restaurantId);
    }

    public List<Restaurant> getAllByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return restaurantRepository.getAll(date);
    }
}