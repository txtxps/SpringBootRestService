package ru.topjava.springboot.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getDishes());
    }
    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, List<Dish> dishes) {
        super(id, name);
    }

    public Restaurant(int restaurantId) {
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name +
                ", dishes=" + dishes + '\'' +
                '}';
    }
}
