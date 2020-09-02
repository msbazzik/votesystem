package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int restaurantId, int userId, LocalDate date);

    Vote get(int restaurantId, int userId, LocalDate date);

    List<Vote> getAll(LocalDate date);
}
