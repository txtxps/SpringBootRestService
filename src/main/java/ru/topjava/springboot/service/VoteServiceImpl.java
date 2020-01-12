package ru.topjava.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.springboot.model.Vote;
import ru.topjava.springboot.repository.RestaurantRepository;
import ru.topjava.springboot.repository.UserRepository;
import ru.topjava.springboot.repository.VoteRepository;
import ru.topjava.springboot.to.VoteTo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    private final VoteRepository voteRepository;

    public VoteServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    @Override
    public VoteTo create(int userId, int restaurantId) {
        Vote vote = getOnDate(userId, LocalDate.now()).orElse(null);
        if (vote != null) {
            throw new DataIntegrityViolationException("");
        }
        Vote newVote = new Vote(userRepository.getOne(userId), restaurantRepository.getOne(restaurantId), LocalDate.now());
        VoteTo voteTo = new VoteTo(newVote, true);
        voteRepository.save(voteTo.getVote());
        return voteTo;
    }

    @Override
    public Optional<Vote> getOnDate(int userId, LocalDate date) {
        return voteRepository.getOnDate(userId, date);
    }

    @Override
    public List<Vote> getBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return voteRepository.getBetween(userId, startDate!=null ? startDate : LocalDate.of(1, 1, 1),
                endDate!=null? endDate : LocalDate.of(3000, 1, 1));
    }

    //    https://habr.com/ru/post/346782/
    @Transactional
    @Override
    public VoteTo createOrUpdate(int userId, int restaurantId) {
        Vote vote = new Vote(userRepository.getOne(userId), restaurantRepository.getOne(restaurantId), LocalDate.now());
        VoteTo voteTo = voteRepository.getOnDate(userId, LocalDate.now()).map(v -> {
            v.setRestaurant(restaurantRepository.getOne(restaurantId));
            return new VoteTo(v, false);
        }).orElseGet(() -> new VoteTo(vote, true));
        voteRepository.save(voteTo.getVote());
        return voteTo;
    }
}
