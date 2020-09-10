package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Role;
import com.javaapp.votesystem.model.User;

import java.util.Collections;
import java.util.Date;

import static com.javaapp.votesystem.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered","votes");

    public static final int NOT_FOUND = 10;
    public static final int USER_ID1 = START_SEQ ;
    public static final int USER_ID2 = START_SEQ + 1;
    public static final int USER_ID3 = START_SEQ + 2;

    public static final int ADMIN_ID = START_SEQ + 3;

    public static final User USER_1 = new User(USER_ID1, "User1", "user1@yandex.ru", "password1", Role.USER);
    public static final User USER_2 = new User(USER_ID2, "User2", "user2@yandex.ru", "password2", Role.USER);
    public static final User USER_3 = new User(USER_ID3, "User3", "user3@yandex.ru", "password3", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER_1);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
