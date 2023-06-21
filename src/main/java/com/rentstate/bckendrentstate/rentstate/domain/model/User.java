package com.rentstate.bckendrentstate.rentstate.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Size(min=2, max=50)
    private String name;

    @NotNull
    @Size(min=2, max=50)
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Integer age = 18;

    @NotNull
    private String gender="";

    @NotNull
    private String description = "";


    @Column(columnDefinition = "FLOAT default 5")
    @Max(value = 5)
    private Float rankPoints = 5.0f;

    @ElementCollection
    @Column(name = "listClients")
    private List<Integer> listClients= new ArrayList<>();

    @ElementCollection
    @Column(name = "listPosts")
    private List<Integer> listPosts= new ArrayList<>();


    public void addClient(int idClient) {
        if (!listClients.contains(idClient)) {
            listClients.add(idClient);
        }
    }

    public void addPost(int idPost) {
        if (!listClients.contains(idPost)) {
            listClients.add(idPost);
        }
    }
}