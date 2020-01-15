package ru.topjava.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.springboot.model.Vote;
import ru.topjava.springboot.repository.VoteRepository;
import ru.topjava.springboot.util.exception.DeadlineVoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private static final LocalTime DEADLINE = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getAllForUser(int userId) {
        return voteRepository.getAll(userId);
    }

    @Transactional
    public void update(Vote vote) {
        boolean isBeforeDeadline = LocalTime.now().isBefore(DEADLINE);
        if (!isBeforeDeadline) {
            throw new DeadlineVoteException("It is after 11:00 then it is too late, vote can't be changed");
        }
        voteRepository.save(vote);
    }

    @Transactional
    public Vote create(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<Vote> getBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return voteRepository.getBetween(userId, startDate!=null ? startDate : LocalDate.of(1, 1, 1),
                endDate!=null? endDate : LocalDate.of(3000, 1, 1));
    }

    public Optional<Vote> getOnDate(int userId, LocalDate date) {
        return voteRepository.getOnDate(userId, date);
    }
}
