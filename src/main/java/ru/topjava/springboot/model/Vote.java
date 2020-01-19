package ru.topjava.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "user_unique_vote_idx")})
public class Vote extends AbstractBaseEntity {

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @Column(name = "restaurant_id")
    @NotNull
    private Integer restaurantId;

    @Column(name = "date", unique = true)
    @NotNull
    private LocalDate date;

    public Vote() {
    }

    public Vote(Integer userId, Integer restaurantId, LocalDate date) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
    }
    public Vote(Integer id, Integer userId, Integer restaurantId, LocalDate date) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public Vote(Vote v) {
        this(v.getId(), v.getUserId(), v.getRestaurantId(), v.getDate());
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                '}';
    }
}