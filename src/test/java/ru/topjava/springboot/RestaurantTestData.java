package ru.topjava.springboot;

import ru.topjava.springboot.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.springboot.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT_ID = START_SEQ + 3;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Petrovich");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Italianec");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 2, "Stolovaya");
    public static final Restaurant RESTAURANT_4 = new Restaurant(RESTAURANT_ID + 3, "Bufet");
    public static final Restaurant RESTAURANT_5 = new Restaurant(RESTAURANT_ID + 4, "Dietka");
    public static final Restaurant RESTAURANT_6 = new Restaurant(RESTAURANT_ID + 5, "Pelmennaya");
    public static final Restaurant RESTAURANT_7 = new Restaurant(RESTAURANT_ID + 6, "Kartoshka");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3,
            RESTAURANT_4, RESTAURANT_5, RESTAURANT_6, RESTAURANT_7);

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID + 6, "Kroshka-Kartoshka");
    }

    public static Restaurant getCreated() {
        return new Restaurant(null, "Riba");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static TestMatchers<Restaurant> RESTAURANT_MATCHERS = TestMatchers.useEquals(Restaurant.class);
}
