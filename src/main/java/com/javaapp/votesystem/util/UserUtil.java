package com.javaapp.votesystem.util;


import com.javaapp.votesystem.model.Role;
import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(),
                userTo.getEmail().toLowerCase(),
                userTo.getPassword(), Role.USER);
    }


    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}