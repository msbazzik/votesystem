package com.javaapp.votesystem.to;

import java.beans.ConstructorProperties;

public class RestaurantToWithVote extends BaseTo {

    private final String name;

    private final int voteCount;

    @ConstructorProperties({"id", "name", "voteCount"})
    public RestaurantToWithVote(Integer id, String name, int voteCount) {
        super(id);
        this.name = name;
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
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
