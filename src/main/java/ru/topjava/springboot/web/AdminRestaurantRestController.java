package ru.topjava.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.service.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(AdminRestaurantRestController.class);

    static final String REST_URL = "/rest/admin/restaurants";

    private final RestaurantService service;

    public AdminRestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant {}");
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}");
        service.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("getAll");
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant {}");
        Restaurant updated = service.update(restaurant, id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(updated.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(updated);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
log.info("create restaurant {}");
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
