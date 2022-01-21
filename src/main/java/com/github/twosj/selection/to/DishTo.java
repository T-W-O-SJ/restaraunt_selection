package com.github.twosj.selection.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends BaseTo {
    String description;
    int price;
    LocalDate localDate;

    public DishTo(Integer id, LocalDate localDate, String description, int price) {
        super(id);
        this.description = description;
        this.price = price;
        this.localDate = localDate;
    }
}
