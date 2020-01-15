package ru.topjava.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "dishes_unique_name_idx")})
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    public Dish() {}

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Dish d) {
        this(d.getId(), d.getName(), d.getPrice());
    }

    public Dish(Integer id, String name, int price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price + '\'' +
                '}';
    }
}
