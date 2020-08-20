package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant, int userId);

    boolean delete(int id, int userId);

    Restaurant getByDate(int id, LocalDate date);

    List<Restaurant> getAll(LocalDate date);

    Restaurant get(int restaurantId);
}