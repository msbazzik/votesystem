package com.javaapp.votesystem.web;

import com.javaapp.votesystem.VoteTestData;
import com.javaapp.votesystem.service.VoteService;
import com.javaapp.votesystem.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.Month;

import static com.javaapp.votesystem.RestaurantTestData.RESTAURANT_ID1;
import static com.javaapp.votesystem.UserTestData.USER_ID1;
import static com.javaapp.votesystem.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {

    public static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteService voteService;

    static final LocalDateTime datetime1 = LocalDateTime.of(2020, Month.AUGUST, 20, 10, 59);
    static final LocalDateTime datetime2 = LocalDateTime.of(2020, Month.AUGUST, 21, 11, 0);

    @Test
    void vote() throws Exception {
        voteService.setClock(datetime1);
        perform(MockMvcRequestBuilders.put(REST_URL + "?restaurantId=100004&date=2020-08-21"))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_MATCHER.contentJson(VoteTestData.getCurrentVote()));
        //compare users?
    }

    @Test
    void delete() throws Exception {
        voteService.setClock(datetime1);
        perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID1 + "?date=2020-08-20"))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.get(VOTE_ID1, USER_ID1));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID1))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE1));
    }

    @Test
    void getAllByDate() throws Exception {
        perform((MockMvcRequestBuilders.get(REST_URL + "?date=2020-08-21")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE2, VOTE5));
    }
}