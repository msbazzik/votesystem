package com.javaapp.votesystem;

import com.javaapp.votesystem.web.restaurant.ProfileRestaurantController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/spring-db.xml", "spring/dispatcher-servlet.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            ProfileRestaurantController restaurantController = appCtx.getBean(ProfileRestaurantController.class);
            System.out.println(restaurantController.getAllWithMenuByDate(LocalDate.of(2020, Month.JANUARY, 30)));
        }
    }
}
