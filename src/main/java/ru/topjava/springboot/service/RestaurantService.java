package ru.topjava.springboot.service;

import org.springframework.security.access.annotation.Secured;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    @Secured({"ROLE_ADMIN"})
    void delete(int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN"})
    Restaurant get(int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN"})
    Restaurant create(Restaurant restaurant);

    @Secured({"ROLE_ADMIN"})
    Restaurant update(Restaurant restaurant, int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN"})
    List<Restaurant> getAll();

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    List<Restaurant> getWithDishes();
}
