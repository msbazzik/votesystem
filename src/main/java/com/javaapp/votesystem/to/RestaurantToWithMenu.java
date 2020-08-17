package com.javaapp.votesystem.to;

import com.javaapp.votesystem.model.Meal;

import java.util.List;

public class RestaurantToWithMenu {
    private final Integer id;

    private final String name;

    private final List<Meal> meals;

    public RestaurantToWithMenu(Integer id, String name, List<Meal> meals) {
        this.id = id;
        this.name = name;
        this.meals = meals;
    }

    public Integer getId() {
        return id;
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
