package com.github.twosj.selection.web.vote;

import com.github.twosj.selection.web.AbstractControllerTest;
import com.github.twosj.selection.web.restaurant.RestaurantTestData;
import com.github.twosj.selection.web.user.UserTestData;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.twosj.selection.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminVoteController.REST_URL + '/';

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void getAllByLocalDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "filter")
                .param("localDate", "2020-01-31"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(votesToByDate));
    }
}