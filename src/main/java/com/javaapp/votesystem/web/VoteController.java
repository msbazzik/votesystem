package com.javaapp.votesystem.web;

import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.service.VoteService;
import com.javaapp.votesystem.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class VoteController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    static final String REST_URL = "/votes";

    @Autowired
    private VoteService voteService;

    @GetMapping(value = "/{date}/{restaurantId}", produces = APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable LocalDate date, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("get vote for restaurant {} for user {} by date {}", restaurantId, userId, date);
        return voteService.get(restaurantId, userId, date);
    }

    @PutMapping(value = "/{date}/{restaurantId}", produces = APPLICATION_JSON_VALUE)
    public Vote vote(@PathVariable LocalDate date, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.debug("vote for restaurant {} for user {}", restaurantId, userId);
        return voteService.vote(restaurantId, userId, date);
    }

    @DeleteMapping(value = "/{date}/{restaurantId}", produces = APPLICATION_JSON_VALUE)
  //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable LocalDate date, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete vote for restaurant {} for user {}", restaurantId, userId);
        voteService.delete(restaurantId, userId, date);
    }
}