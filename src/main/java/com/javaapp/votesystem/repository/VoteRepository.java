package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int voteId, int userId);

    Vote get(int voteId, int userId);

    Vote getByUserByDate(int userId, LocalDate date);

    List<Vote> getAllByDate(LocalDate date);
}
