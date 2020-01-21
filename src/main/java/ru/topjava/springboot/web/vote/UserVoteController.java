package ru.topjava.springboot.web.vote;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.springboot.model.Vote;
import ru.topjava.springboot.service.VoteService;
import ru.topjava.springboot.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {

    private static final Logger log = LoggerFactory.getLogger(UserVoteController.class);
    static final String REST_URL = "/rest";


    private final VoteService service;

    public UserVoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping("/votes")
    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("get all votes for user with id={}", userId);
        return service.getAllForUser(userId).stream()
                .map(Vote::new)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@PathVariable("restaurantId") int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Vote created = new Vote(userId, restaurantId, LocalDate.now());
        log.info("create vote for restaurant with id={} for user with id={}", restaurantId, userId);
        service.create(created);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/votes/{id}")
                .buildAndExpand(created.getRestaurantId(), created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("restaurantId") int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Vote updated = new Vote(userId, restaurantId, LocalDate.now());
        log.info("update vote for restaurant with id={} for user with id={}", restaurantId, userId);
        service.update(updated);
    }
    @GetMapping("votes/history")
    public List<Vote> getBetween(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        log.info("get votes between dates({} - {})", startDate, endDate);
        return service.getBetween(userId, startDate, endDate);
    }



//
//    @GetMapping
//    public Vote getOnDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        int userId = SecurityUtil.authUserId();
//        log.info("get vote for user with id ={}, date ={}", userId, date);
//        return service.getOnDate(userId, date).get();
//    }
}
