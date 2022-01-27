package com.github.twosj.selection.web.vote;

import com.github.twosj.selection.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.List;

import static com.github.twosj.selection.web.user.UserTestData.ADMIN_MAIL;
import static com.github.twosj.selection.web.user.UserTestData.USER_MAIL;
import static com.github.twosj.selection.web.vote.ProfileVoteController.FIX_CLOSE_TIME;
import static com.github.twosj.selection.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileVoteController.REST_URL +"/";

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL).param("id", "1"))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(VOTE_MATCHER.contentJson(newVote));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        if (LocalTime.now().isBefore(FIX_CLOSE_TIME)) {
            perform(MockMvcRequestBuilders.put(REST_URL).param("id","2"))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andExpect(VOTE_MATCHER.contentJson(updateVote));
        } else perform(MockMvcRequestBuilders.put(REST_URL).param("id","2"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getBetween() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL+"filter")
                .param("startDate", "2020-01-29")
                .param("endDate", "2021-01-31"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_TO_MATCHER.contentJson(List.of(voteTo1)));
    }

}