package com.github.twosj.selection.web.restaurant;

import com.github.twosj.selection.model.Dish;
import com.github.twosj.selection.repository.DishRepository;
import com.github.twosj.selection.util.JsonUtil;
import com.github.twosj.selection.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.twosj.selection.web.restaurant.RestaurantTestData.*;
import static com.github.twosj.selection.web.user.UserTestData.ADMIN_MAIL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminMenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/admin/restaurants/";

    @Autowired
    private DishRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = RestaurantTestData.getUpdatedDish();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RestaurantTestData.DISH_MATCHER.assertMatch(repository.get(RESTAURANT1_ID, DISH1_ID).orElse(null), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Dish newDish = RestaurantTestData.getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = RestaurantTestData.DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        RestaurantTestData.DISH_MATCHER.assertMatch(created, newDish);
        RestaurantTestData.DISH_MATCHER.assertMatch(repository.getById(newId), newDish);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(repository.get(RestaurantTestData.RESTAURANT1_ID, DISH1_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByRestaurantAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT2_ID + "/dishes/")
                .param("localDate", "2020-01-31"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish4,dish5));
    }
}