package com.javaapp.votesystem.repository.datajpa;

import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int restaurantId) {
        return crudRestaurantRepository.delete(restaurantId) != 0;
    }

    @Override
    public Restaurant getByDate(int id, LocalDate date) {
        return crudRestaurantRepository.getByDate(id, date);
    }

    @Override
    public List<Restaurant> getAll(LocalDate date) {
        return crudRestaurantRepository.getAll(date);
    }

    @Override
    public Restaurant get(int restaurantId) {
        return crudRestaurantRepository.findById(restaurantId).orElse(null);
    }
}