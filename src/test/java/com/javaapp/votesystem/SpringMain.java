package com.javaapp.votesystem;

import com.javaapp.votesystem.web.RestaurantController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/applicationContext.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            RestaurantController restaurantController = appCtx.getBean(RestaurantController.class);
            System.out.println(restaurantController.getAllWithVotesByDate(LocalDate.of(2020, Month.JANUARY, 30)));
        }
    }
}
