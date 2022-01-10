package com.git.selection.web.vote;

import com.git.selection.repository.VoteRepository;
import com.git.selection.to.VoteTo;
import com.git.selection.util.VoteUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminVoteController {
    static final String REST_URL = "/api/admin/votes";

    VoteRepository voteRepository;

    @GetMapping("/{id}")
    @Operation(summary = "Get all votes for restaurant by it's id ")
    public List<VoteTo> getAllByRestaurant(
            @PathVariable int id) {
        return VoteUtil.getTos(voteRepository.getAllForRestaurant(id));
    }

    @GetMapping("/")
    @Operation(summary = "History of votes")
    public List<VoteTo> getAll() {
        return VoteUtil.getTos(voteRepository.getAllHistory());
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter history of all votes")
    public List<VoteTo> getAllByLocalDate(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return VoteUtil.getTos(voteRepository.getAllByLocalDate(localDate));
    }

}
