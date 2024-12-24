
package com.example.Lab3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
public class UserPE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name="PASS_HASH", nullable = false)
    private String passHash;
}
