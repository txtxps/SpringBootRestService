package ru.topjava.springboot.to;

import ru.topjava.springboot.model.Dish;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.repository.RestaurantRepository;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DishTo {

    private String name;

    private int price;

    private Integer restaurantId;

    public DishTo() {}

    public DishTo(String name, int price, Integer restaurantId) {
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

//    public DishTo(Dish dish) {
//        name = dish.getName();
//        price = dish.getPrice();
//        restaurantId = dish.getRestaurant().getId();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
