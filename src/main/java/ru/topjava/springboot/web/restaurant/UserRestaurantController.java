package ru.topjava.springboot.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.repository.RestaurantRepository;

import java.util.List;

import static ru.topjava.springboot.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController {

    private static final Logger log = LoggerFactory.getLogger(UserRestaurantController.class);

    static final String REST_URL = "/rest";

    private final RestaurantRepository repository;

    public UserRestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/restaurants/{id}/dishes")
    public Restaurant getWithDishes(@PathVariable int id) {
        log.info("get restaurant with id={} with dishes", id);
        return checkNotFoundWithId(repository.getWithDishes(id), id);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("get all restaurants");
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }
}
