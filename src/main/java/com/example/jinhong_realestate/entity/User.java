package com.example.jinhong_realestate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "member")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;

    private String preferredLocation;
    private int minRent;
    private int maxRent;
    private int minPrice;
    private int maxPrice;

}
