package com.petstore.data.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
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

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Store store;
}
