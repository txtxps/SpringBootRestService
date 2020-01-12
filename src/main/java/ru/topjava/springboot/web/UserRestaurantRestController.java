package ru.topjava.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(UserRestaurantRestController.class);

    static final String REST_URL = "/rest/restaurants";

    private final RestaurantService service;

    public UserRestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<Restaurant>> getWithDishes() {
        log.info("get restaurant {} with dishes");
        List<Restaurant> withDishes = service.getWithDishes();
        return new ResponseEntity<>(withDishes, HttpStatus.OK);
    }
}
