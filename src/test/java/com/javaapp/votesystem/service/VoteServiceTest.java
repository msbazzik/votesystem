package com.javaapp.votesystem.service;

import com.javaapp.votesystem.VoteTestData;
import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.util.exception.NotFoundException;
import com.javaapp.votesystem.util.exception.VotingTimeIsOverException;
import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

class VoteServiceTest extends AbstractServiceTest {


    @Autowired
    private VoteService service;

    static final LocalDateTime datetime1 = LocalDateTime.of(2020, Month.AUGUST, 20, 10, 59);
    static final LocalDateTime datetime2 = LocalDateTime.of(2020, Month.AUGUST, 21, 11, 0);
    static final LocalDateTime datetime3 = LocalDateTime.of(2020, Month.AUGUST, 20, 12, 0);


    @Test
    void voteBeforeEndTime() {
        service.setClock(datetime1);
        Vote newVote = VoteTestData.getNew();
        Vote createdVote = service.vote(newVote.getRestaurant().getId(), newVote.getUser().getId(), newVote.getDate());
        Integer id = createdVote.getId();
        newVote.setId(id);
        VOTE_MATCHER.assertMatch(createdVote, newVote);
        VOTE_MATCHER.assertMatch(service.get(createdVote.getId(), USER_ID2), newVote);
    }

    @Test
    void voteSecondTimeAfterEndTime() {
        service.setClock(datetime3);
        assertThrows(VotingTimeIsOverException.class, () -> service.vote(RESTAURANT_ID2, USER_ID1, DATE_1));
    }

    @Test
    void voteFirstTimeAfterEndTime() {
        service.setClock(datetime2);
        VOTE_MATCHER.assertMatch(service.vote(RESTAURANT_ID1, USER_ID2, DATE_2), getCurrentVote());
    }

    @Test
    void deleteBeforeEndTime() {
        service.setClock(datetime1);
        service.delete(VOTE_ID1, USER_ID1, DATE_1);
        assertThrows(NotFoundException.class, () -> service.get(VOTE_ID1, USER_ID1));

    }

    @Test
    void deleteAfterEndTime() {
        service.setClock(datetime2);
        assertThrows(VotingTimeIsOverException.class, () -> service.delete(VOTE_ID1, USER_ID1, DATE_1));

    }

    @Test
    void deletedNotFoundByRestaurant() {
        service.setClock(datetime1);
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID1, DATE_1));
    }

    @Test
    void get() {
        Vote vote = service.get(VOTE_ID1, USER_ID1);
        VOTE_MATCHER.assertMatch(vote, VOTE1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID1));
    }

    @Test
    void getAllByDate() {
        List<Vote> votes = service.getAllByDate(DATE_1);
        VOTE_MATCHER.assertMatch(votes, VOTE1, VOTE3, VOTE4, VOTE6);
    }

    @Test
    void createWithException() throws Exception {
        service.setClock(LocalDateTime.of(2020, Month.AUGUST, 21, 10, 59));
        validateRootCause(() -> service.vote(0, USER_ID2, LocalDate.of(2020, Month.AUGUST, 21)), ConstraintViolationException.class);
        validateRootCause(() -> service.vote(RESTAURANT_ID1, 0, LocalDate.of(2020, Month.AUGUST, 21)), ConstraintViolationException.class);
        validateRootCause(() -> service.vote(RESTAURANT_ID1, USER_ID2, null), ConstraintViolationException.class);
    }
}