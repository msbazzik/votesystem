package com.javaapp.votesystem.to;

import com.javaapp.votesystem.model.Meal;

import java.util.List;

public class RestaurantToWithMenu extends BaseTo{

    private final String name;

    private final List<Meal> meals;

    public RestaurantToWithMenu(Integer id, String name, List<Meal> meals) {
        super(id);
        this.name = name;
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RestaurantToWithVote{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meals=" + meals +
                '}';
    }
}
