package com.git.selection.web.vote;

import com.git.selection.model.Vote;
import com.git.selection.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = VoteProfileRestService.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteProfileRestService  {
    static final String REST_URL = "/profile/vote";

    @Autowired
    VoteService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@Valid @RequestBody Vote vote) {
        if (service.getTodayVote() != null) {
            service.update(vote);
        }
        Vote created =service.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/filter")
    public List<Vote> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate) {
        return service.getBetweenDates(startDate, endDate);
    }
}
