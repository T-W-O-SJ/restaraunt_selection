package com.git.selection.model;

import com.git.selection.util.validation.NoHtml;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@ToString(callSuper = true)
public class Dish extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @NoHtml
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull
    private int price;

    @Column(name = "local_date", nullable = false)
    @NotNull
    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String description, int price, LocalDate date) {
        this.description = description;
        this.price = price;
        this.localDate = date;
    }

    public Dish(Integer id, String description, int price, LocalDate date) {
        super(id);
        this.description = description;
        this.price = price;
        this.localDate = date;
    }
}
