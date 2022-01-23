package com.github.twosj.selection.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {
    @NotNull
    private final Integer restaurantId;
    @NotNull
    private final LocalDate localDate;

    public VoteTo(Integer id, LocalDate localDate, Integer restaurantId) {
        super(id);
        this.localDate = localDate;
        this.restaurantId = restaurantId;

    }

}
