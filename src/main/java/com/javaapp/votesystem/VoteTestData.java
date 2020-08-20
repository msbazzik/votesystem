package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Vote;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.javaapp.votesystem.RestaurantTestData.*;
import static com.javaapp.votesystem.UserTestData.*;
import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE_ID1 = START_SEQ + 8;
    public static final int VOTE_ID2 = START_SEQ + 9;
    public static final int VOTE_ID3 = START_SEQ + 10;
    public static final int VOTE_ID4 = START_SEQ + 11;
    public static final int VOTE_ID5 = START_SEQ + 12;
    public static final int VOTE_ID6 = START_SEQ + 13;
    public static final int VOTE_ID7 = START_SEQ + 14;
    public static final int VOTE_ID8 = START_SEQ + 15;
    public static final int VOTE_ID9 = START_SEQ + 16;

    public static final Vote VOTE1 = new Vote(VOTE_ID1, LocalDate.of(2020, Month.JANUARY, 30),
            USER_1, RESTAURANT1);
    public static final Vote VOTE2 = new Vote(VOTE_ID2, LocalDate.of(2020, Month.JANUARY, 30),
            USER_2, RESTAURANT1);
    public static final Vote VOTE3 = new Vote(VOTE_ID3, LocalDate.of(2020, Month.JANUARY, 30),
            USER_3, RESTAURANT1);

    public static final Vote VOTE4 = new Vote(VOTE_ID4, LocalDate.of(2020, Month.JANUARY, 31),
            USER_1, RESTAURANT2);
    public static final Vote VOTE5 = new Vote(VOTE_ID5, LocalDate.of(2020, Month.JANUARY, 31),
            USER_2, RESTAURANT1);
    public static final Vote VOTE6 = new Vote(VOTE_ID6, LocalDate.of(2020, Month.JANUARY, 31),
            USER_3, RESTAURANT3);

    public static final Vote VOTE7 = new Vote(VOTE_ID7, LocalDate.of(2020, Month.FEBRUARY, 1),
            USER_1, RESTAURANT4);
    public static final Vote VOTE8 = new Vote(VOTE_ID8, LocalDate.of(2020, Month.FEBRUARY, 1),
            USER_2, RESTAURANT2);
    public static final Vote VOTE9 = new Vote(VOTE_ID9, LocalDate.of(2020, Month.FEBRUARY, 1),
            USER_3, RESTAURANT2);


    public static final List<Vote> VOTES = Arrays.asList(VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7, VOTE8, VOTE9);
}
