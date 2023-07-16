package com.InstaGram.InstaGram.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpOutput {
   private Boolean signUpStatus;
   private String singUpMessage;
}
