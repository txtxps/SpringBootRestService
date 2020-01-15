package ru.topjava.springboot.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.springboot.model.Dish;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.repository.DishRepository;
import ru.topjava.springboot.repository.RestaurantRepository;
import ru.topjava.springboot.to.DishTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.topjava.springboot.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {

    private static final Logger log = LoggerFactory.getLogger(AdminDishRestController.class);

    static final String REST_URL = "/admin/dishes";

    @Autowired
    private DishRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish with id={}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish with id={}", id);
        checkNotFoundWithId(repository.delete(id), id);;
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAll() {
        log.info("get all dishes");
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody DishTo dishTo, @PathVariable int id) {
        log.info("update dish with id={}", id);
        Restaurant restaurant = restaurantRepository.getOne(dishTo.getRestaurantId());
        Dish updated = new Dish(id, dishTo.getName(), dishTo.getPrice(), restaurant);
        checkNotFoundWithId(repository.save(updated), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody DishTo dishTo) {
        log.info("create dish {} for restaurant with id={}", dishTo, dishTo.getRestaurantId());
        Restaurant restaurant = restaurantRepository.getOne(dishTo.getRestaurantId());
        Dish created = new Dish(null, dishTo.getName(), dishTo.getPrice(), restaurant);
        repository.save(created);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
