package com.github.twosj.selection.web.vote;

import com.github.twosj.selection.repository.VoteRepository;
import com.github.twosj.selection.to.VoteTo;
import com.github.twosj.selection.util.VoteUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminVoteController {
    static final String REST_URL = "/api/admin/votes";

    VoteRepository voteRepository;

    @GetMapping("/filter")
    @Operation(summary = "Filter history of all votes")
    public List<VoteTo> getAllByLocalDate(@RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        log.info("Get votes for {}",localDate);
        return VoteUtil.getTos(voteRepository.getAllByLocalDate(localDate));
    }
}
