package com.git.selection.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Restaurant( Restaurant r) {
      this(r.id,r.name,r.description,r.address,r.phone);
    }

    public Restaurant(Integer id, String name, @NotBlank @Size(max = 100) String description, @NotBlank @Size(max = 100) String address, @NotBlank @Size(max = 100) String phone) {
        super(id, name);
        this.description = description;
        this.address = address;
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
