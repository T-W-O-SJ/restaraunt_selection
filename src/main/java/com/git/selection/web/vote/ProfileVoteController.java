package com.git.selection.web.vote;

import com.git.selection.model.Vote;
import com.git.selection.repository.RestaurantRepository;
import com.git.selection.repository.UserRepository;
import com.git.selection.repository.VoteRepository;
import com.git.selection.to.VoteTo;
import com.git.selection.util.VoteUtil;
import com.git.selection.web.AuthUser;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.git.selection.util.DateTimeUtil.endDayOrMax;
import static com.git.selection.util.DateTimeUtil.startDayOrMin;
import static com.git.selection.util.validation.ValidationUtil.checkDateConsistent;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileVoteController {
    static final String REST_URL = "/api/profile/votes";

    VoteRepository voteRepository;
    UserRepository userRepository;
    RestaurantRepository restaurantRepository;

    @PostMapping(value = "/{restaurant_id}")
    @Operation(summary = "Vote for restaurant by restaurant id")
    public ResponseEntity<Vote> createOrUpdate(@AuthenticationPrincipal AuthUser authUser, @PathVariable("restaurant_id") int id) {
        int userId = authUser.id();
        log.info("{} vote for user {}", LocalDateTime.now(), userId);

        Vote currentVote = voteRepository.getByDate(userId, LocalDate.now()).orElse(null);
        Vote updateVote = new Vote();
        if (currentVote != null) {
            try {
                checkDateConsistent(LocalDateTime.now());
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
            updateVote.setId(currentVote.id());
        }
        updateVote.setLocalDate(LocalDate.now());
        updateVote.setRestaurant(restaurantRepository.getById(id));
        updateVote.setUser(userRepository.getById(userId));
        return new ResponseEntity<>(voteRepository.save(updateVote), HttpStatus.OK);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter history of user votes")
    public List<VoteTo> getBetween(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int userId = authUser.id();
        return VoteUtil.getTos(voteRepository.getBetweenDates(startDayOrMin(startDate),endDayOrMax(endDate), userId));
    }
}
