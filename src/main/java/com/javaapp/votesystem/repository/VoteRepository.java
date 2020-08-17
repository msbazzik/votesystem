package com.javaapp.votesystem.repository;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId);

    boolean delete(int id, int userId);

    Vote getByUserId(int userId);

    List<Vote> getAll(int userId);

    List<Vote> findAllByDate(LocalDate date);



    //Map<Restaurant, Integer> getVoteCountsByRestaurant();
}
