package com.git.selection.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "local_date"}, name = "votes_unique_user_date_idx")})
@Getter
@Setter
@ToString(callSuper = true)
public class Vote extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "local_date", nullable = false)
    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference
    private User user;

    public Vote(LocalDate dateTime, Restaurant restaurant ){
        this.localDate = dateTime;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, LocalDate dateTime, Restaurant restaurant) {
        super(id);
        this.localDate = dateTime;
        this.restaurant = restaurant;
    }

    public Vote() {

    }

}
