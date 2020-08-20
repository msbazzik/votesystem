package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_ID1 = START_SEQ + 4;
    public static final int RESTAURANT_ID2 = START_SEQ + 5;
    public static final int RESTAURANT_ID3 = START_SEQ + 6;
    public static final int RESTAURANT_ID4 = START_SEQ + 7;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID1, "restaurant" + RESTAURANT_ID1);
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "restaurant" + RESTAURANT_ID2);
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID3, "restaurant" + RESTAURANT_ID3);
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT_ID4, "restaurant" + RESTAURANT_ID4);

    public static final List<Restaurant> restaurants = Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);
}
