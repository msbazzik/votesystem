package com.javaapp.votesystem.web.restaurant;

import com.javaapp.votesystem.AuthorizedUser;
import com.javaapp.votesystem.service.RestaurantService;
import com.javaapp.votesystem.to.RestaurantToWithMenu;
import com.javaapp.votesystem.util.RestaurantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(ProfileRestaurantController.class);

    static final String REST_URL = "/profile/restaurants/dishes";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(params = "date")
    public List<RestaurantToWithMenu> getAllWithMenuByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                           @AuthenticationPrincipal AuthorizedUser authUser) {
        LOG.info("get all restaurants with menu by date {}", date);
        return RestaurantUtil.getRestaurantsToWithMenu(restaurantService.getAllByDate(date));
    }
}
