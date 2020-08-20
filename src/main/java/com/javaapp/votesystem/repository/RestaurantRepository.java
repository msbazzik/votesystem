package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant, int userId);

    boolean delete(int id, int userId);

    Restaurant getByDate(int id, LocalDate date);

    List<Restaurant> getAll(LocalDate date);

    boolean deleteMenu(int id, int userId, LocalDate date);

    List<Meal> createMenu(Integer restaurantId, int userId, LocalDate date, List<Meal> meals);

    Restaurant get(int restaurantId);
}
