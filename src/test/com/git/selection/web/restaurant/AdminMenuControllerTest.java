package com.git.selection.web.restaurant;

import com.git.selection.model.Dish;
import com.git.selection.repository.DishRepository;
import com.git.selection.util.JsonUtil;
import com.git.selection.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.git.selection.web.restaurant.RestaurantTestData.*;
import static com.git.selection.web.user.UserTestData.ADMIN_MAIL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminMenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminMenuController.REST_URL + '/';

    @Autowired
    private DishRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = getUpdatedDish();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT1_ID + "/" + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(repository.get(RESTAURANT1_ID, DISH1_ID).orElse(null), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Dish newDish = getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(repository.getById(newId), newDish);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT1_ID + "/" + DISH1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(repository.get(RESTAURANT1_ID, DISH1_ID).isPresent());
    }
}