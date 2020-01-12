package ru.topjava.springboot.service;

import org.springframework.security.access.annotation.Secured;
import ru.topjava.springboot.model.Dish;
import ru.topjava.springboot.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    @Secured({"ROLE_ADMIN"})
    public void delete(int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN"})
    public Dish get(int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN"})
    public List<Dish> getAll();

    @Secured({"ROLE_ADMIN"})
    public Dish create(Dish dish);

    @Secured({"ROLE_ADMIN"})
    public void update(Dish dish, int id) throws NotFoundException;
}
