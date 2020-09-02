package com.javaapp.votesystem.repository.datajpa;

import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.repository.VoteRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudVoteRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository) {
        this.crudVoteRepository = crudVoteRepository;
    }

    @Override
    public Vote save(Vote vote) {
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int restaurantId, int userId, LocalDate date) {
        return crudVoteRepository.delete(restaurantId, userId, date) != 0;
    }

    @Override
    public Vote get(int restaurantId, int userId, LocalDate date) {
        return crudVoteRepository.get(restaurantId, userId, date);
    }

    @Override
    public List<Vote> getAll(LocalDate date) {
        return crudVoteRepository.getAll(date);
    }
}
