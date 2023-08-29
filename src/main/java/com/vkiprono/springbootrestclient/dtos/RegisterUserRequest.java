package com.vkiprono.springbootrestclient.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.vkiprono.springbootrestclient.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest implements Serializable {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private Role role;
}
