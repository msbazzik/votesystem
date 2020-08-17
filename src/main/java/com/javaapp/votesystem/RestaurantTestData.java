package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int restaurant_ID = START_SEQ + 2;

    public static final Restaurant restaurant1 = new Restaurant(restaurant_ID + 1, "restaurant" + (restaurant_ID + 1));
    public static final Restaurant restaurant2 = new Restaurant(restaurant_ID + 2, "restaurant" + (restaurant_ID + 2));
    public static final Restaurant restaurant3 = new Restaurant(restaurant_ID + 3, "restaurant" + (restaurant_ID + 3));
    public static final Restaurant restaurant4 = new Restaurant(restaurant_ID + 4, "restaurant" + (restaurant_ID + 4));

    public static final List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2, restaurant3, restaurant4);

}
