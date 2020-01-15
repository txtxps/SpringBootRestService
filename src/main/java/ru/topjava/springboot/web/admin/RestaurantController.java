package ru.topjava.springboot.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.repository.RestaurantRepository;

import java.net.URI;
import java.util.List;

import static ru.topjava.springboot.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    static final String REST_URL = "/admin/restaurants";

    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant with id={}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant");
        checkNotFoundWithId(repository.delete(id), id);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("get all restaurants");
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/dishes")
    public ResponseEntity<List<Restaurant>> getWithDishes(@PathVariable int id) {
        log.info("get restaurant with id={}, with dishes", id);
        return new ResponseEntity<>(repository.getWithDishes(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant {}", restaurant);
        Restaurant updated = checkNotFoundWithId(repository.save(restaurant), id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(updated.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(updated);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
