package com.javaapp.votesystem.util.exception;

public class VotingTimeIsOverException extends RuntimeException {
    public VotingTimeIsOverException(String message) {
        super(message);
    }
}