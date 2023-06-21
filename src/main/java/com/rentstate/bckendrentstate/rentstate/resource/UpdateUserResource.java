package com.rentstate.bckendrentstate.rentstate.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResource {

    private String name;

    private String lastName;

    private Integer age;

    private String email;

    private String password;

    private String gender;

    private String description;

}
