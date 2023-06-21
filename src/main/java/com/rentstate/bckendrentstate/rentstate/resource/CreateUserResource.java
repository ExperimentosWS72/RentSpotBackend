package com.rentstate.bckendrentstate.rentstate.resource;


import lombok.Getter;

@Getter
public class CreateUserResource {
    private String name;

    private String lastName;

    private String email;

    private String password;
}
