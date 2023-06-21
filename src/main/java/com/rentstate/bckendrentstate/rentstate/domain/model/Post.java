package com.rentstate.bckendrentstate.rentstate.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String characteristics;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    private Float price;

    @NotNull
    @NotBlank
    private String imgUrl;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    private Boolean disponible = true;

    @NotNull
    private Long userAuthorId;

    @NotNull
    private Integer userRentId = 0;

}