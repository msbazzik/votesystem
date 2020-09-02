package com.javaapp.votesystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
  //  @OrderBy("date DESC")
    private List<Meal> meals;

//    public Restaurant(String name) {
//        this(null, name);
//    }

    public Restaurant() {
    }

    // @ConstructorProperties({"id", "name"})
    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}