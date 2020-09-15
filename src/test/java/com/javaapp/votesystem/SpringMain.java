package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Role;
import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.model.Vote;
import com.javaapp.votesystem.web.user.AdminRestController;
import com.javaapp.votesystem.web.vote.ProfileVoteController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static com.javaapp.votesystem.UserTestData.USER_1;
import static com.javaapp.votesystem.VoteTestData.VOTE_ID1;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/spring-db.xml", "spring/dispatcher-servlet.xml")) {

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            System.out.println();

            TestUtil.mockAuthorize(USER_1);

            ProfileVoteController voteController = appCtx.getBean(ProfileVoteController.class);
            Vote vote = voteController.get(VOTE_ID1);

            System.out.println(vote.toString());
        }
    }
}
