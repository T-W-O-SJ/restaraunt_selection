package com.github.twosj.selection.to;

import com.github.twosj.selection.HasIdAndEmail;
import com.github.twosj.selection.util.validation.NoHtml;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo implements HasIdAndEmail {
    @Email
    @NotBlank
    @Size(max = 128)
    @NoHtml
    String email;

    String description;

    String phone;

    public RestaurantTo(Integer id, String name, String email, String description, String phone) {
        super(id, name);
        this.email = email;
        this.description = description;
        this.phone = phone;
    }
}
