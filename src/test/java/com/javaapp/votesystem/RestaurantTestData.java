package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.to.RestaurantToWithVote;

import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class, "meals");
    public static TestMatcher<RestaurantToWithVote> RESTAURANT_VOTE_MATCHER = TestMatcher.usingFieldsComparator(RestaurantToWithVote.class);

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT_ID1 = START_SEQ + 4;
    public static final int RESTAURANT_ID2 = START_SEQ + 5;
    public static final int RESTAURANT_ID3 = START_SEQ + 6;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID1, "restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "restaurant2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID3, "restaurant3");

    public static final RestaurantToWithVote RESTAURANT_TO1 = new RestaurantToWithVote(100006, "restaurant3", 1);
    public static final RestaurantToWithVote RESTAURANT_TO2 = new RestaurantToWithVote(100005, "restaurant2", 1);

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID1, "UpdatedName");
    }
}
