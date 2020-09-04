package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Restaurant;
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

    public Meal createMeal(Meal meal, int restaurantId) {
        Assert.notNull(meal, "restaurant must not be null");
        return mealRepository.save(meal, restaurantId);
    }

    public void updateMeal(Meal meal, int restaurantId) {
        Assert.notNull(meal, "restaurant must not be null");
        checkNotFoundWithId(mealRepository.save(meal, restaurantId), meal.getId());
    }

    public void deleteMeal(int mealId, int restaurantId) {
        checkNotFoundWithId(mealRepository.delete(mealId, restaurantId), mealId);
    }

    public Meal get(int mealId, int restaurantId) {
        return checkNotFoundWithId(mealRepository.get(mealId, restaurantId), mealId);
    }
}