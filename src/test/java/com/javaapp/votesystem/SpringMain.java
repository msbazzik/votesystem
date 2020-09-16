package com.javaapp.votesystem;

import com.javaapp.votesystem.model.Role;
import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.to.RestaurantToWithMenu;
import com.javaapp.votesystem.web.restaurant.ProfileRestaurantController;
import com.javaapp.votesystem.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.javaapp.votesystem.UserTestData.USER_1;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/spring-db.xml", "spring/dispatcher-servlet.xml")) {

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            System.out.println();

            TestUtil.mockAuthorize(USER_1);

            ProfileRestaurantController controller = appCtx.getBean(ProfileRestaurantController.class);
            List<RestaurantToWithMenu> list = controller.getAllWithMenuByDate(LocalDate.of(2020, Month.AUGUST, 20));

            System.out.println(list.toString());
        }
    }
}
