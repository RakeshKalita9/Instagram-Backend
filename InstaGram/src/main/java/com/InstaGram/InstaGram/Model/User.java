package com.InstaGram.InstaGram.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Min(value = 18,message = "You are Not adult")
    private Integer age;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;

}
