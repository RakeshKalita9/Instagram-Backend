package com.InstaGram.InstaGram.Model;

import com.InstaGram.InstaGram.Model.Enums.PostType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
    private String postData;
    @Enumerated(EnumType.STRING)
    private PostType postType;


    @ManyToOne
    private User user;
}
