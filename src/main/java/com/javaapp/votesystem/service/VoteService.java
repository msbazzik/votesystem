package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Vote;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteService {

    public List<Vote> getAllByDate(LocalDate date) {
        return null;
    }
}
