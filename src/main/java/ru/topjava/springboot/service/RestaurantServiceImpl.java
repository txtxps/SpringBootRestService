package ru.topjava.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.repository.RestaurantRepository;
import ru.topjava.springboot.util.exception.NotFoundException;

import java.util.List;

import static ru.topjava.springboot.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, int id) throws NotFoundException {
        return checkNotFoundWithId(repository.save(restaurant), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Restaurant> getWithDishes() {
        return repository.getWithDishes();
    }
}
