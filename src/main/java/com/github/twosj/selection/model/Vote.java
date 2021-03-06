package com.github.twosj.selection.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "local_date"}, name = "votes_unique_user_date_idx")})
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Vote extends BaseEntity {

    @Column(name = "local_date", nullable = false)
    @NotNull
    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference("restaurantToVotes")
    @NotNull
    @ToString.Exclude
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("userToVotes")
    @NotNull
    @ToString.Exclude
    private User user;

    public Vote(Integer id, LocalDate localDate, Restaurant restaurant) {
        super(id);
        this.localDate = localDate;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, LocalDate localDate) {
        super(id);
        this.localDate = localDate;
    }

}
