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
    public boolean delete(int voteId, int userId) {
        return crudVoteRepository.delete(voteId, userId) != 0;
    }

    @Override
    public Vote get(int voteId, int userId) {
        return crudVoteRepository.get(voteId, userId);
    }

    @Override
    public Vote getByUserByDate(int userId, LocalDate date) {
        return crudVoteRepository.getByUserByDate(userId, date);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return crudVoteRepository.getAllByDate(date);
    }
}