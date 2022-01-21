package com.github.twosj.selection.web.restaurant;

import com.github.twosj.selection.model.Dish;
import com.github.twosj.selection.repository.DishRepository;
import com.github.twosj.selection.util.JsonUtil;
import com.github.twosj.selection.web.AbstractControllerTest;
import com.github.twosj.selection.web.user.UserTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminMenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminMenuController.REST_URL + '/';

    @Autowired
    private DishRepository repository;

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = RestaurantTestData.getUpdatedDish();
        perform(MockMvcRequestBuilders.put(REST_URL + RestaurantTestData.RESTAURANT1_ID + "/" + RestaurantTestData.DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RestaurantTestData.DISH_MATCHER.assertMatch(repository.get(RestaurantTestData.RESTAURANT1_ID, RestaurantTestData.DISH1_ID).orElse(null), updated);
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void create() throws Exception {
        Dish newDish = RestaurantTestData.getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RestaurantTestData.RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = RestaurantTestData.DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        RestaurantTestData.DISH_MATCHER.assertMatch(created, newDish);
        RestaurantTestData.DISH_MATCHER.assertMatch(repository.getById(newId), newDish);
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RestaurantTestData.RESTAURANT1_ID + "/" + RestaurantTestData.DISH1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(repository.get(RestaurantTestData.RESTAURANT1_ID, RestaurantTestData.DISH1_ID).isPresent());
    }
}