package com.javaapp.votesystem.repository.datajpa;

import com.javaapp.votesystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.restaurant.id=:restaurantId AND v.user.id=:userId AND v.date=:date")
    int delete(@Param("restaurantId") int restaurantId, @Param("userId") int id, @Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId AND v.user.id=:userId AND v.date=:date")
    Vote get(@Param("restaurantId") int restaurantId, @Param("userId") int userId, @Param("date") LocalDate date);

    @Query("SELECT DISTINCT v FROM Vote v WHERE v.date=:date")
    List<Vote> getAll(@Param("date") LocalDate date);
}