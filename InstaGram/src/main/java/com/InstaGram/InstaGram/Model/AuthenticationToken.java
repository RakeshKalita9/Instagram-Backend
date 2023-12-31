package com.InstaGram.InstaGram.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime tokenCreationDate;

    @OneToOne
    private User user  ;

    public AuthenticationToken(User user){
        this.tokenValue=UUID.randomUUID().toString();
        this.tokenCreationDate = LocalDateTime.now();
        this.user=user;
    }





}
