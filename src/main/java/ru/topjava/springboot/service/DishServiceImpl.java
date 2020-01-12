package ru.topjava.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.springboot.model.Dish;
import ru.topjava.springboot.repository.DishRepository;
import ru.topjava.springboot.util.exception.NotFoundException;

import java.util.List;

import static ru.topjava.springboot.util.ValidationUtil.checkNotFoundWithId;


@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll();
    }

    @Override
    public Dish create(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public void update(Dish dish, int id) throws NotFoundException {
        checkNotFoundWithId(repository.save(dish), id);
    }
}
