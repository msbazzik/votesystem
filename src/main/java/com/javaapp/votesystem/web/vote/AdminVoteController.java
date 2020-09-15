package com.javaapp.votesystem.web.vote;

import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminVoteController.class);

    static final String REST_URL = "/admin/votes";

    @Autowired
    private VoteService voteService;

    @GetMapping(params = "date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("get all votes by date {}", date);
        return voteService.getAllByDate(date);
    }
}
