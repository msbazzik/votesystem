package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Vote;

import java.time.LocalDate;
import java.time.Month;

import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT1;
import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT3;
import static com.javaapp.votesystem.UserTestData.USER_2;
import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator(Vote.class, "user", "restaurant");

    public static final int NOT_FOUND = 10;
    public static final int VOTE_ID1 = START_SEQ + 17;
    public static final int VOTE_ID2 = START_SEQ + 18;
    public static final int VOTE_ID3 = START_SEQ + 19;
    public static final int VOTE_ID4 = START_SEQ + 20;
    public static final int VOTE_ID5 = START_SEQ + 21;
    public static final int VOTE_ID6 = START_SEQ + 22;

    public static final LocalDate DATE_1 = LocalDate.of(2020, Month.AUGUST, 20);
    public static final LocalDate DATE_2 = LocalDate.of(2020, Month.AUGUST, 21);

    public static final Vote VOTE1 = new Vote(VOTE_ID1, DATE_1);
    public static final Vote VOTE2 = new Vote(VOTE_ID2, DATE_2);
    public static final Vote VOTE3 = new Vote(VOTE_ID3, DATE_1);
    public static final Vote VOTE4 = new Vote(VOTE_ID4, DATE_1);
    public static final Vote VOTE5 = new Vote(VOTE_ID5, DATE_2);
    public static final Vote VOTE6 = new Vote(VOTE_ID6, DATE_1);

    public static Vote getNew() {
        Vote vote = new Vote(null, DATE_2);
        vote.setRestaurant(RESTAURANT1);
        vote.setUser(USER_2);
        return vote;
    }

    public static Vote getCurrentVote() {
        Vote vote = new Vote(100023, DATE_2);
        vote.setRestaurant(RESTAURANT1);
        vote.setUser(USER_2);
        return vote;
    }
}
