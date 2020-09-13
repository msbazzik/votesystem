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

import java.time.*;
import java.util.List;

import static com.javaapp.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    private Clock clock;
    private ZoneId zoneId;

    public static final LocalTime VOTE_END_TIME = LocalTime.of(11, 0);


    @Autowired
    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        clock = Clock.systemDefaultZone();
        this.zoneId = ZoneId.systemDefault();
    }

    //for JUnit test only
    public void setClock(LocalDateTime dateTime) {
        this.clock = Clock.fixed(dateTime.atZone(zoneId).toInstant(), zoneId);
    }

    public Vote vote(int restaurantId, int userId, LocalDate date) {
        Vote vote = getByUserByDate(userId, date);
        if (vote == null && checkDate(date)) {
            return save(userId, restaurantId, date);
        } else if (vote != null && checkDateTime(date)) {
            return update(vote, restaurantId);
        } else {
            throw new VotingTimeIsOverException("Vote time: " + VOTE_END_TIME + " is over.");
        }
    }

    private boolean checkDate(LocalDate date) {
        return date == null || date.equals(LocalDate.now(clock)) || date.isAfter(LocalDate.now(clock));
    }

    private boolean checkDateTime(LocalDate date) {
        if (date == null || (LocalTime.now(clock).isBefore(VOTE_END_TIME) && date.equals(LocalDate.now(clock)))) {
            return true;
        } else return date.isAfter(LocalDate.now(clock));
    }

    private Vote save(int userId, int restaurantId, LocalDate date) {
        User user = userRepository.get(userId);
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        Vote vote = new Vote(date);
        vote.setUser(user);
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    private Vote update(Vote vote, int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    public void delete(int voteId, int userId, LocalDate date) {
        if (checkDateTime(date)) {
            checkNotFoundWithId(voteRepository.delete(voteId, userId), voteId);
        } else {
            throw new VotingTimeIsOverException("Vote time: " + VOTE_END_TIME + " is over.");
        }
    }

    public Vote get(int voteId, int userId) {
        return checkNotFoundWithId(voteRepository.get(voteId, userId), voteId);
    }

    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.getAllByDate(date);
    }

    public Vote getByUserByDate(int userId, LocalDate date) {
        return voteRepository.getByUserByDate(userId, date);
    }
}
