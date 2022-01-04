package com.git.selection.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.git.selection.util.validation.NoHtml;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@ToString(callSuper = true)
public class Restaurant extends NamedEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String phone;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "restaurant" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("localDate desc ")
    @JsonManagedReference
    @ToString.Exclude
    private List<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "restaurant" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("localDate desc ")
    @JsonManagedReference
    @ToString.Exclude
    private List<Vote> dishes;

    public Restaurant() {
    }
    public Restaurant( Restaurant r) {
      this(r.id,r.name,r.email,r.phone);
    }

    public Restaurant(Integer id, String name, @NotBlank @Size(max = 100) String address, @NotBlank @Size(max = 100) String phone) {
        super(id, name);
        this.email = address;
        this.phone = phone;
    }

}
