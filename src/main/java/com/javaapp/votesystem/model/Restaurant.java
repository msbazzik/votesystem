package com.javaapp.votesystem.model;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.List;


public class Restaurant extends AbstractNamedEntity {
    protected List<Meal> meals;

    public Restaurant(String name) {
        this(null, name);
    }

    @ConstructorProperties({"id", "name"})
    public Restaurant(Integer id, String name) {
        super(id, name);
    }

//    public void setVotes(Set<Vote> votes) {
//        this.votes = votes;
//    }


    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}