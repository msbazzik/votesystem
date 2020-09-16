package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Meal;

public interface MealRepository {

    Meal save(Meal meal, int restaurantId);

    boolean delete(int id, int restaurantId);

    Meal get(int id, int restaurantId);
}