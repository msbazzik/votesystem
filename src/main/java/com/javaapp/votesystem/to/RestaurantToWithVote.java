package com.javaapp.votesystem.to;

public class RestaurantToWithVote {
    private Integer id;

    private String name;

    private int voteCount;

    public RestaurantToWithVote() {
    }

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
