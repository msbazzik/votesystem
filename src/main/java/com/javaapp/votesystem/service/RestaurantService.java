package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Restaurant;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {
    public Restaurant create(Restaurant restaurant, int userId) {
        return null;
    }

    public void delete(int id, int userId) {
    }

    public void update(Restaurant restaurant, int userId) {
    }

    public Restaurant get(int id, LocalDate date) {
        return null;
    }

    public List<Restaurant> getAllByDate(LocalDate date) {
        return null;
    }
}