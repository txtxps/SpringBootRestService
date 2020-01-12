package ru.topjava.springboot.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.topjava.springboot.model.Restaurant;
import ru.topjava.springboot.model.Vote;
import ru.topjava.springboot.service.VoteService;
import ru.topjava.springboot.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = UserVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteRestController {

    private static final Logger log = LoggerFactory.getLogger(UserVoteRestController.class);
    static final String REST_URL = "/rest/vote";
    private static final LocalTime DEADLINE = LocalTime.of(21, 0);

    private final VoteService service;

    public UserVoteRestController(VoteService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Restaurant> vote(@PathVariable("id") int restaurantId) {
        int userId = SecurityUtil.authUserId();
        boolean isBeforeDeadline = LocalTime.now().isBefore(DEADLINE);
        VoteTo voteTo = isBeforeDeadline ? service.createOrUpdate(userId, restaurantId) : service.create(userId, restaurantId);
        return new ResponseEntity<>(voteTo.getVote().getRestaurant(), voteTo.isCreated() ? HttpStatus.CREATED : (isBeforeDeadline ? HttpStatus.OK : HttpStatus.CONFLICT));
    }

    @GetMapping("/history")
    public List<Vote> getBetween(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        log.info("get votes between dates({} - {})", startDate, endDate);
        return service.getBetween(userId, startDate, endDate);
    }

    @GetMapping
    public Vote getOnDate(@RequestParam(value = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote for user with id ={}, day ={}", userId, date);
        return service.getOnDate(userId, date).get();
    }
}
