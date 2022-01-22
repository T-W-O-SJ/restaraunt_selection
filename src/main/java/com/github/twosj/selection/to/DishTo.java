package com.github.twosj.selection.to;

import com.github.twosj.selection.util.validation.NoHtml;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends BaseTo {
    @NotBlank
    @Size(min = 2, max = 120)
    @NoHtml
    String description;

    @NotNull
    int price;

    @NotNull
    LocalDate localDate;

    public DishTo(Integer id, LocalDate localDate, String description, int price) {
        super(id);
        this.description = description;
        this.price = price;
        this.localDate = localDate;
    }
}
