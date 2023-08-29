package com.vkiprono.springbootrestclient.models;

import com.vkiprono.springbootrestclient.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    private String firstName;
    private String lastName;

    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
