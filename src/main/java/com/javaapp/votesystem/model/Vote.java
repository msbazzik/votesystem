package com.javaapp.votesystem.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity{

    private LocalDateTime dateTime;

    private String user;

    private Restaurant restaurant;

    public Vote(LocalDateTime dateTime, String user, Restaurant restaurant) {
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, LocalDateTime dateTime, String user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getUser() {
        return user;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
