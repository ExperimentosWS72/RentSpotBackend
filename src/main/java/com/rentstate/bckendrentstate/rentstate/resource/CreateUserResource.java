package com.rentstate.bckendrentstate.rentstate.resource;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserResource {
    private String name;

    private String lastName;

    private String email;

    private String password;
}
