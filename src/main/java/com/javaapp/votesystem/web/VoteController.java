package com.javaapp.votesystem.web;

import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.service.VoteService;
import com.javaapp.votesystem.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    static final String REST_URL = "/votes";

    @Autowired
    private VoteService voteService;

    @PutMapping(params = {"restaurantId", "date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@RequestParam int restaurantId,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int userId = SecurityUtil.authUserId();
        LOG.debug("vote for restaurant {} for user {}", restaurantId, userId);
        Vote vote = voteService.getByUserByDate(userId, date);
        if (vote == null) {
            vote = voteService.vote(restaurantId, userId, date);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(vote.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(vote);
        } else {
            vote = voteService.vote(restaurantId, userId, date);
            return ResponseEntity.status(HttpStatus.OK).body(vote);
        }
    }

    @DeleteMapping(value = "/{voteId}", params = "date", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int voteId,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete vote {} for user {}", voteId, userId);
        voteService.delete(voteId, userId, date);
    }

    @GetMapping(value = "/{voteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable int voteId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("get vote {} for user {}", voteId, userId);
        return voteService.get(voteId, userId);
    }

    //check admin here
    @GetMapping(params = "date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("get all votes by date {}", date);
        return voteService.getAllByDate(date);
    }
}