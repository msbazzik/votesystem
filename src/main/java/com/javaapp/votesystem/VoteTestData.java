package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Vote;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.javaapp.votesystem.RestaurantTestData.*;

public class VoteTestData {
    public static final Vote vote1 = new Vote(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
            "User 1", restaurant1);
    public static final Vote vote2 = new Vote(LocalDateTime.of(2020, Month.JANUARY, 30, 9, 0),
            "User 2", restaurant1);
    public static final Vote vote3 = new Vote(LocalDateTime.of(2020, Month.JANUARY, 30, 11, 0),
            "User 3", restaurant1);

    public static final Vote vote4 = new Vote(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0),
            "User 1", restaurant2);
    public static final Vote vote5 = new Vote(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0),
            "User 2", restaurant1);
    public static final Vote vote6 = new Vote(LocalDateTime.of(2020, Month.JANUARY, 31, 15, 15),
            "User 3", restaurant3);

    public static final Vote vote7 = new Vote(LocalDateTime.of(2020, Month.FEBRUARY, 1, 11, 0),
            "User 1", restaurant1);
    public static final Vote vote8 = new Vote(LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0),
            "User 2", restaurant2);
    public static final Vote vote9 = new Vote(LocalDateTime.of(2020, Month.FEBRUARY, 1, 1, 0),
            "User 3", restaurant2);


    public static final List<Vote> votes = Arrays.asList(vote1, vote2, vote3, vote4, vote5, vote6, vote7, vote8, vote9);
}
