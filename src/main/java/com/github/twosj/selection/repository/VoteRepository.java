package com.github.twosj.selection.repository;

import com.github.twosj.selection.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v  WHERE v.user.id =:userId and v.localDate =:localDate")
    Optional<Vote> getByDate(int userId, LocalDate localDate);

    @Query("SELECT v FROM Vote v WHERE v.localDate=:localDate ORDER BY v.restaurant.id ASC")
    List<Vote> getAllByLocalDate(LocalDate localDate);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.localDate >= :startDate AND v.localDate <= :endDate ORDER BY v.localDate DESC")
    List<Vote> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId);
}


