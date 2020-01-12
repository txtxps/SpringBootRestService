package ru.topjava.springboot.service;

import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import ru.topjava.springboot.model.Vote;
import ru.topjava.springboot.to.VoteTo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteService {

    @Secured({"ROLE_USER"})
    VoteTo create(int userId, int restaurantId);

    Optional<Vote> getOnDate(int userId, LocalDate localDate);

    @Secured({"ROLE_USER"})
    List<Vote> getBetween(int userId, LocalDate startDate, LocalDate endDate);

    @Secured({"ROLE_USER"})
    VoteTo createOrUpdate(int userId, int restaurantId);
}
