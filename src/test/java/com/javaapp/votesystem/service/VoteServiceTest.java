package com.javaapp.votesystem.service;

import com.javaapp.votesystem.RestaurantTestData;
import com.javaapp.votesystem.VoteTestData;
import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.util.exception.NotFoundException;
import com.javaapp.votesystem.util.exception.VotingTimeIsOverException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT_ID1;
import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT_ID2;
import static com.javaapp.votesystem.UserTestData.USER_ID1;
import static com.javaapp.votesystem.UserTestData.USER_ID2;
import static com.javaapp.votesystem.VoteTestData.*;
import static org.junit.Assert.assertThrows;

public class VoteServiceTest extends AbstractServiceTest {


    @Autowired
    private VoteService service;

    public static final LocalDateTime datetime1 = LocalDateTime.of(2020, Month.AUGUST, 20, 10, 59);
    public static final LocalDateTime datetime2 = LocalDateTime.of(2020, Month.AUGUST, 21, 11, 0);


    @Test
    public void getAllByDate() {
        List<Vote> votes = service.getAllByDate(DATE_1);
        VOTE_MATCHER.assertMatch(votes, VOTE1, VOTE3, VOTE4, VOTE6);
    }

    @Test
    public void voteBeforeEndTime() {
        service.setClock(datetime1);
        Vote newVote = VoteTestData.getNew();
        Vote createdVote = service.vote(newVote.getRestaurant().getId(), newVote.getUser().getId(), newVote.getDate());
        Integer id = createdVote.getId();
        newVote.setId(id);
        VOTE_MATCHER.assertMatch(createdVote, newVote);
        VOTE_MATCHER.assertMatch(service.get(RESTAURANT_ID1, USER_ID2, DATE_2), newVote);
    }

    @Test
    public void voteAfterEndTime() {
        service.setClock(datetime2);
        assertThrows(VotingTimeIsOverException.class, () -> service.vote(RESTAURANT_ID1, USER_ID2, DATE_2));
    }

    @Test
    public void deleteBeforeEndTime() {
        service.setClock(datetime1);
        service.delete(RESTAURANT_ID1, USER_ID1, DATE_1);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_ID1, USER_ID1, DATE_1));

    }

    @Test
    public void deleteAfterEndTime() {
        service.setClock(datetime2);
        assertThrows(VotingTimeIsOverException.class, () -> service.delete(RESTAURANT_ID1, USER_ID1, DATE_1));

    }

//    @Test
//    public void deletedNotFoundByUser() {
//        service.setClock(datetime1);
//        assertThrows(NotFoundException.class, () -> service.delete(RESTAURANT_ID1, NOT_FOUND, DATE_1));
//    }

    @Test
    public void deletedNotFoundByRestaurant() {
        service.setClock(datetime1);
        assertThrows(NotFoundException.class, () -> service.delete(RestaurantTestData.NOT_FOUND, USER_ID1, DATE_1));
    }


    @Test
    public void get() {
        Vote vote = service.get(RESTAURANT_ID1, USER_ID1, DATE_1);
        VOTE_MATCHER.assertMatch(vote, VOTE1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_ID2, USER_ID1, DATE_1));
    }

    @Test
    public void createWithException() throws Exception {
        service.setClock(LocalDateTime.of(2020, Month.AUGUST, 21, 10, 59));
        validateRootCause(() -> service.vote(0, USER_ID2, LocalDate.of(2020, Month.AUGUST, 21)), ConstraintViolationException.class);
        validateRootCause(() -> service.vote(RESTAURANT_ID1, 0, LocalDate.of(2020, Month.AUGUST, 21)), ConstraintViolationException.class);
        //?   validateRootCause(() -> service.vote(RESTAURANT_ID1, USER_ID2, null), ConstraintViolationException.class);
    }
}