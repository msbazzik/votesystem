package com.javaapp.votesystem.web;

import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.to.MenuTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class MealController {
    private static final Logger LOG = LoggerFactory.getLogger(MealController.class);

    static final String REST_URL = "/menus";

    @GetMapping(value = "/{id}/{date}", produces = APPLICATION_JSON_VALUE)
    public MenuTo get(@PathVariable int id, @PathVariable LocalDate date) {
//        int userId = SecurityUtil.authUserId();
//        LOG.info("get vote {} for user {}", id, userId);
//        return voteService.get(id, userId);
        return null;
    }

    @PostMapping(value = "/{id}/{date}", produces = APPLICATION_JSON_VALUE)
    public MenuTo create(@PathVariable int id, @PathVariable LocalDate date, @RequestBody MenuTo menuTo) {
//        int userId = SecurityUtil.authUserId();
//        checkNew(vote);
//        LOG.debug("vote for " + userId);
//        return voteService.vote(vote, userId);
        return null;
    }

    @DeleteMapping(value = "/{id}/{date}", produces = APPLICATION_JSON_VALUE)
    //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable LocalDate date) {
//        int userId = SecurityUtil.authUserId();
//        LOG.info("delete vote {} for user {}", id, userId);
//        voteService.delete(id, userId);
    }

}
