package com.vkiprono.springbootrestclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {
    //Email - Used as the username
    private String userName;

    private String password;

}
