package ru.topjava.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.springboot.model.Vote;
import ru.topjava.springboot.service.VoteService;

import java.time.LocalDate;

import static ru.topjava.springboot.RestaurantTestData.RESTAURANT_3;
import static ru.topjava.springboot.RestaurantTestData.RESTAURANT_6;
import static ru.topjava.springboot.UserTestData.USER_1;
import static ru.topjava.springboot.UserTestData.USER_2;
import static ru.topjava.springboot.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    @Autowired
    private VoteService voteService;

    public static final int VOTE_ID = START_SEQ + 16;


    public static TestMatchers<Vote> VOTE_MATCHERS = TestMatchers.useEquals(Vote.class);
}
