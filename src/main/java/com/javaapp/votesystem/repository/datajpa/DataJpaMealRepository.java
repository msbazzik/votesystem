package com.javaapp.votesystem.repository.datajpa;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.repository.MealRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudMealRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMealRepository(CrudMealRepository crudMealRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return crudMealRepository.findById(id)
                .filter(meal -> meal.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }
}