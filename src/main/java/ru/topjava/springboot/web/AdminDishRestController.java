package ru.topjava.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.springboot.model.Dish;
import ru.topjava.springboot.service.DishService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {

    private static final Logger log = LoggerFactory.getLogger(AdminDishRestController.class);

    static final String REST_URL = "/rest/admin/dishes";

    private final DishService service;

    public AdminDishRestController(DishService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish {}");
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish {}");
        service.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAll() {
        log.info("getAll");
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish {}");
        service.update(dish, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        Dish created = service.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        log.info("create dish {}");
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
