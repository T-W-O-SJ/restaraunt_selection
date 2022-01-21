package com.github.twosj.selection.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.twosj.selection.HasIdAndEmail;
import com.github.twosj.selection.util.validation.NoHtml;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"dishes", "votes"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity implements HasIdAndEmail {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 128)
    @NoHtml   // https://stackoverflow.com/questions/17480809
    private String email;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @NoHtml
    private String description;

    @Column(name = "phone", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("localDate desc ")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference("restaurantToVotes")
    private List<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("localDate desc ")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference("restaurantToDishes")
    private List<Dish> dishes;

    public Restaurant(Restaurant r) {
        this(r.id, r.name, r.email, r.description, r.phone);
    }

    public Restaurant(Integer id, String name, String email, @NotBlank @Size(max = 100) String description, @NotBlank @Size(max = 15) String phone) {
        super(id, name);
        this.email = email;
        this.description = description;
        this.phone = phone;
    }

}
