package com.git.selection.web.vote;

import com.git.selection.error.NotFoundException;
import com.git.selection.model.Vote;
import com.git.selection.repository.VoteRepository;
import com.git.selection.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteAdminRestService.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteAdminRestService {
    static final String REST_URL = "/api/admin/votes";

    VoteRepository voteRepository;


    @GetMapping("/{id}")
    public Vote get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        int userId = authUser.id();
        return voteRepository.get(userId, id).orElseThrow(() -> new NotFoundException("not found"));
    }


    @GetMapping("/")
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @GetMapping("/filter")
    public List<Vote> getAllByLocalDate(
            @RequestParam @Nullable LocalDate localDate) {
        return voteRepository.getAllByLocalDate(localDate);
    }
}
