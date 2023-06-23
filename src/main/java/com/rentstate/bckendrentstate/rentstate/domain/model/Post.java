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
    private String description;

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

    private Boolean available = true;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private User renter;

}