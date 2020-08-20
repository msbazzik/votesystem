package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Meal;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int restaurantId, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int restaurantId, int userId);

//    // null if meal do not belong to userId
//    Meal get(int id, int userId);
//
//    // ORDERED dateTime desc
//    List<Meal> getAll(int userId);
}