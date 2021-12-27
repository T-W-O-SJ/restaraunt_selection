package com.git.selection.web.vote;

import com.git.selection.model.Vote;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAdminRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/admin/votes";


    @Override
    @GetMapping("/{id}")
    public Vote get(@PathVariable  int id) {
        return super.get(id);
    }

    @GetMapping("/filter")
    public List<Vote> getBetween(
            @RequestParam int restaurantId,
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate){
        return super.getBetweenDates(restaurantId,startDate, endDate);
    }
}
