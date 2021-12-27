package com.git.selection.web.vote;

import com.git.selection.model.Vote;
import com.git.selection.repository.datajpa.DataJpaVoteRepository;
import com.git.selection.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = VoteProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteProfileRestController extends AbstractVoteController{
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataJpaVoteRepository voteRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@Valid @RequestBody Vote vote) {
         if(super.getTodayVote()!=null){
             super.update(vote)
         }
        Vote created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
