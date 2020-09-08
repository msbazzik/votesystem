package com.javaapp.votesystem.service;

import com.javaapp.votesystem.model.Restaurant;
import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.repository.RestaurantRepository;
import com.javaapp.votesystem.repository.UserRepository;
import com.javaapp.votesystem.repository.VoteRepository;
import com.javaapp.votesystem.util.exception.VotingTimeIsOverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.javaapp.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public static final LocalTime VOTE_END_TIME = LocalTime.of(11, 0);


    @Autowired
    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Vote> getAllByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return voteRepository.getAll(date);
    }

    public Vote vote(int restaurantId, int userId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        Vote vote = get(restaurantId, userId, date);
        if (vote == null && checkDateTime(date)) {
            return save(userId, restaurantId, date);
        } else if (vote != null && checkDateTime(date)) {
            return update(vote, restaurantId);
        } else {
            throw new VotingTimeIsOverException("Vote time: " + VOTE_END_TIME + " is over.");
        }
    }

    private boolean checkDateTime(LocalDate date) {
        if (LocalTime.now().isBefore(VOTE_END_TIME) && date.equals(LocalDate.now())) {
            return true;
        } else return date.isAfter(LocalDate.now());
    }

    public Vote save(int userId, int restaurantId, LocalDate date) {
        User user = userRepository.get(userId);
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        Vote vote = new Vote(date);
        vote.setUser(user);
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    public Vote update(Vote vote, int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    public void delete(int restaurantId, int userId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        if (checkDateTime(date)) {
            checkNotFoundWithId(voteRepository.delete(restaurantId, userId, date), restaurantId);
        } else {
            throw new VotingTimeIsOverException("Vote time: " + VOTE_END_TIME + " is over.");
        }
    }

    public Vote get(int restaurantId, int userId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return checkNotFoundWithId(voteRepository.get(restaurantId, userId, date), restaurantId);
    }
}
