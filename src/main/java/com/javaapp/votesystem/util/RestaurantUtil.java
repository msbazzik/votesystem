package com.javaapp.votesystem.util;

import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.to.RestaurantToWithMenu;
import com.javaapp.votesystem.to.RestaurantToWithVote;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static List<RestaurantToWithVote> getRestaurantsToWithVoteCount(List<Restaurant> restaurants, List<Vote> votes) {
        Map<Restaurant, List<Vote>> votesMap = new LinkedHashMap<>();
        votes.forEach(
                vote -> votesMap
                        .computeIfAbsent(vote.getRestaurant(), d -> new ArrayList<>())
                        .add(vote)
        );
        return votesMap.entrySet()
                .stream()
                .map(e -> new RestaurantToWithVote(e.getKey().getId(), e.getKey().getName(), e.getValue().size()))
                .collect(Collectors.toList());
    }

    public static List<RestaurantToWithMenu> getRestaurantsToWithMenu(List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .map(RestaurantUtil::createToWithMenu)
                .collect(Collectors.toList());
    }

    public static RestaurantToWithMenu createToWithMenu(Restaurant restaurant) {
        return new RestaurantToWithMenu(restaurant.getId(), restaurant.getName(), restaurant.getMeals());
    }
}