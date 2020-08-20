package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.javaapp.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal createMeal(Meal meal, int restaurantId, int userId) {
        Assert.notNull(meal, "restaurant must not be null");
        return mealRepository.save(meal, restaurantId, userId);
    }

    public void updateMeal(Meal meal, int restaurantId, int userId) {
        Assert.notNull(meal, "restaurant must not be null");
        checkNotFoundWithId(mealRepository.save(meal, restaurantId, userId), meal.getId());
    }

    public void deleteMeal(int mealId, int restaurantId, int userId) {
        checkNotFoundWithId(mealRepository.delete(mealId, restaurantId, userId), mealId);
    }
}