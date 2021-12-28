package com.git.selection.web.vote;

import com.git.selection.model.Vote;
import com.git.selection.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteAdminRestService.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAdminRestService  {
    static final String REST_URL = "/admin/votes";

    @Autowired
    VoteService service;

    @GetMapping("/{id}")
    public Vote get(@PathVariable  int id) {
        return service.get(id);
    }


    @GetMapping("/")
    public List<Vote> getAll() {
        return service.getAll();
    }

    @GetMapping("/filter")
    public List<Vote> getAllByLocalDate(
            @RequestParam @Nullable LocalDate localDate) {
        return service.getAllByLocalDate(localDate);
    }
}
