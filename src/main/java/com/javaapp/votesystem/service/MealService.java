package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {
    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal createMeal(Meal meal, int restaurantId, int userId) {
        return null;
    }

    public void updateMeal(Meal meal, int restaurantId, int userId) {
    }

    public void deleteMeal(int mealId, int restaurantId, int userId) {
    }
}
