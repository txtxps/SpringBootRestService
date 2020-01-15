package ru.topjava.springboot.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.springboot.model.Dish;
import ru.topjava.springboot.repository.DishRepository;

import java.net.URI;
import java.util.List;

import static ru.topjava.springboot.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    private static final Logger log = LoggerFactory.getLogger(DishController.class);

    static final String REST_URL = "/admin/dishes";

    @Autowired
    private DishRepository repository;

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
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish with id={}", id);
        checkNotFoundWithId(repository.save(dish), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        Dish created = repository.save(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        log.info("create dish {}", dish);
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
