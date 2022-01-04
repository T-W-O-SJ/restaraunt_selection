package com.git.selection.web.vote;

import com.git.selection.model.Vote;
import com.git.selection.repository.RestaurantRepository;
import com.git.selection.repository.UserRepository;
import com.git.selection.repository.VoteRepository;
import com.git.selection.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.git.selection.util.validation.ValidationUtil.checkDateConsistent;


@RestController
@RequestMapping(value = VoteProfileRestService.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteProfileRestService {
    static final String REST_URL = "/api/profile/votes";

    VoteRepository voteRepository;
    UserRepository userRepository;
    RestaurantRepository restaurantRepository;


    @PostMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createOrUpdate(@AuthenticationPrincipal AuthUser authUser, @PathVariable("id") int id) {
        int userId = authUser.id();
        log.info("{} vote for user {}",LocalDateTime.now() , userId);

       Vote currentVote = checkDateConsistent(voteRepository.getByDate(userId,LocalDate.now()).orElse(null), LocalDateTime.now());
        Vote updateVote = new Vote();
if(currentVote!=null){
    updateVote.setId(currentVote.id());
}
updateVote.setLocalDate(LocalDate.now());
updateVote.setRestaurant(restaurantRepository.getById(id));
updateVote.setUser(userRepository.getById(userId));
return  new ResponseEntity<>(voteRepository.save(currentVote), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<Vote> getBetween(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate) {
        int userId = authUser.id();
        return voteRepository.getBetweenDates(userId,startDate, endDate);
    }
}
