package com.javaapp.votesystem.to;

import java.beans.ConstructorProperties;

public class RestaurantToWithVote {
    private final Integer id;

    private final String name;

    private final int voteCount;

    //
//    public RestaurantToWithVote() {
//    }
    @ConstructorProperties({"id","name", "voteCount"})
    public RestaurantToWithVote(Integer id, String name, int voteCount) {
        this.id = id;
        this.name = name;
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
