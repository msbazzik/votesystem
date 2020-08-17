package com.javaapp.votesystem.to;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class MenuTo {

    private LocalDate date;

    private Integer restaurantId;

    private List<Meal> meals;

    public MenuTo(LocalDate date, Integer restaurantid)//, List<Meal> meals)
     {
        this.date = date;
        this.restaurantId = restaurantid;
        //this.meals = meals;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "date=" + date +
                ", restaurant=" + restaurantId +
                ", meals=" + meals +
                '}';
    }
}
