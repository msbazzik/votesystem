package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Restaurant;

import java.util.List;

import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class,"meals");

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT_ID1 = START_SEQ + 4;
    public static final int RESTAURANT_ID2 = START_SEQ + 5;
    public static final int RESTAURANT_ID3 = START_SEQ + 6;
    // public static final int RESTAURANT_ID4 = START_SEQ + 7;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID1, "restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "restaurant2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID3, "restaurant3");
    //   public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT_ID4, "restaurant4");

    public static final List<Restaurant> restaurants = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID1, "UpdatedName");
    }
}
