package com.github.twosj.selection.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {

    Integer restaurantId;
    LocalDate localDate;


    public VoteTo(Integer id, LocalDate localDate, Integer restaurantId) {
        super(id);
        this.localDate = localDate;
        this.restaurantId = restaurantId;

    }

}
