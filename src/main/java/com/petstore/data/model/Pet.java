package com.petstore.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private Integer age;

    private String color;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Gender petGender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    private Store store;
}
