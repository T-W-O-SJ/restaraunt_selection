package com.git.selection.to;

import java.time.LocalDate;

public class VoteTo extends BaseTo {

    private int restaurantId;

    private LocalDate localDate = LocalDate.now();

    public VoteTo(Integer id, int restaurantId, LocalDate localDate, int userId) {
        super(id);
        this.restaurantId = restaurantId;
        this.localDate = localDate;
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;
}
