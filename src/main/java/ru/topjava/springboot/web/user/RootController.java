package ru.topjava.springboot.web.user;

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
@RequestMapping(value = RootController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    static final String REST_URL = "/rest/restaurants";

    private final RestaurantService service;

    public RootController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<Restaurant>> getWithDishes() {
        log.info("get restaurants with dishes");
        List<Restaurant> withDishes = service.getWithDishes();
        return new ResponseEntity<>(withDishes, HttpStatus.OK);
    }
}
