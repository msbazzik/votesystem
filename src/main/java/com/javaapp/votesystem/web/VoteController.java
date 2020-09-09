package com.javaapp.votesystem.web;

import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.service.VoteService;
import com.javaapp.votesystem.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    static final String REST_URL = "/votes";

    @Autowired
    private VoteService voteService;

    @GetMapping(value = "/{date}/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable LocalDate date, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("get vote for restaurant {} for user {} by date {}", restaurantId, userId, date);
        return voteService.get(restaurantId, userId, date);
    }

//    @PutMapping(value = "/{date}/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Vote vote(@PathVariable LocalDate date, @PathVariable int restaurantId) {
//        int userId = SecurityUtil.authUserId();
//        LOG.debug("vote for restaurant {} for user {}", restaurantId, userId);
//        return voteService.vote(restaurantId, userId, date);
//    }

    @PutMapping(value = "/{date}/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable LocalDate date, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.debug("vote for restaurant {} for user {}", restaurantId, userId);
        Vote created = voteService.vote(restaurantId, userId, date);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{date}/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable LocalDate date, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete vote for restaurant {} for user {}", restaurantId, userId);
        voteService.delete(restaurantId, userId, date);
    }
}