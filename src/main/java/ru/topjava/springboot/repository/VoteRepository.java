package ru.topjava.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.springboot.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date DESC")
    List<Vote> getBetween(@Param("userId") int userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> getOnDate(@Param("userId") int userId, @Param("date") LocalDate date);
}
