package com.github.twosj.selection.web.vote;

import com.github.twosj.selection.model.Vote;
import com.github.twosj.selection.repository.RestaurantRepository;
import com.github.twosj.selection.repository.UserRepository;
import com.github.twosj.selection.repository.VoteRepository;
import com.github.twosj.selection.to.VoteTo;
import com.github.twosj.selection.util.DateTimeUtil;
import com.github.twosj.selection.util.VoteUtil;
import com.github.twosj.selection.util.validation.ValidationUtil;
import com.github.twosj.selection.web.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.github.twosj.selection.util.validation.ValidationUtil.notFound;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileVoteController {
    static final String REST_URL = "/api/profile/votes/";
    public static final LocalTime FIX_CLOSE_TIME = LocalTime.of(11, 0);

    VoteRepository voteRepository;
    UserRepository userRepository;
    RestaurantRepository restaurantRepository;

    @PostMapping
    @Operation(summary = "Vote for restaurant by restaurant id")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VoteTo> create(@AuthenticationPrincipal AuthUser authUser, @RequestParam @NotNull int id) {
        int userId = authUser.id();
        log.info("{} vote for user {}", LocalDateTime.now(), userId);
        Vote todayVote = voteRepository.getByDate(userId, LocalDate.now()).orElse(null);
        if (todayVote != null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Vote createVote = new Vote();
        createVote.setLocalDate(LocalDate.now());
        createVote.setRestaurant(restaurantRepository.getById(id));
        createVote.setUser(userRepository.getById(userId));
        return new ResponseEntity<>(VoteUtil.createTo(voteRepository.save(createVote)), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Vote for restaurant by restaurant id")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<VoteTo>update(@AuthenticationPrincipal AuthUser authUser, @RequestParam @NotNull int id) {
        int userId = authUser.id();
        log.info("{} update vote for user {}", LocalDateTime.now(), userId);
        Vote currentVote = voteRepository.getByDate(userId, LocalDate.now()).orElseThrow(notFound("No vote for update"));
        Vote updateVote = new Vote();
        ValidationUtil.checkDateConsistent(LocalDateTime.now());
        updateVote.setId(currentVote.id());
        updateVote.setLocalDate(LocalDate.now());
        updateVote.setRestaurant(restaurantRepository.getById(id));
        updateVote.setUser(userRepository.getById(userId));
        return new ResponseEntity<>( VoteUtil.createTo(voteRepository.save(updateVote)),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter history of user votes")
    public List<VoteTo> getBetween(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int userId = authUser.id();
        return VoteUtil.getTos(voteRepository.getBetweenDates(DateTimeUtil.startDayOrMin(startDate), DateTimeUtil.endDayOrMax(endDate), userId));
    }
}
