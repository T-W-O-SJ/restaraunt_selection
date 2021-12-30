package com.git.selection.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "restaurants")
@Getter
@Setter
@ToString(callSuper = true)
public class Restaurant extends NamedEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "description", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String description;

    @Column(name = "address", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String address;

    @Column(name = "phone", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String phone;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "restaurant" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("localDate desc ")
    @JsonManagedReference
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant( Restaurant r) {
      this(r.id,r.name,r.description,r.address,r.phone);
    }

    public Restaurant(Integer id, String name, @NotBlank @Size(max = 100) String description, @NotBlank @Size(max = 100) String address, @NotBlank @Size(max = 100) String phone) {
        super(id, name);
        this.description = description;
        this.address = address;
        this.phone = phone;
    }

}
