package com.vkiprono.springbootrestclient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateUserRequest implements Serializable {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}
