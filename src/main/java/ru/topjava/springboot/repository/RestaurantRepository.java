package ru.topjava.springboot.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.springboot.model.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    List<Restaurant> getWithDishes(int id);

    @Query("SELECT r FROM Restaurant r ORDER BY r.name")
    List<Restaurant> getAll();

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);
}
