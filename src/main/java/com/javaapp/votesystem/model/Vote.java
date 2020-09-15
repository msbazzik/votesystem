package com.javaapp.votesystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id", "restaurant_id"}, name = "votes_unique_date_user_restaurant_idx")})
public class Vote extends AbstractBaseEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate date) {
        this(null, date);
    }

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
