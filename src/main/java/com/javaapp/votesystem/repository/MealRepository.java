package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Meal;

public interface MealRepository {
    // null if updated meal do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to restaurantId
    Meal get(int id, int restaurantId);

    // ORDERED dateTime desc
    //List<Meal> getAll(int restaurantId);
}