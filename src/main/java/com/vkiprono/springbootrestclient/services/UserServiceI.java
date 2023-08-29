package com.vkiprono.springbootrestclient.services;

import com.vkiprono.springbootrestclient.dtos.AuthenticateUserRequest;
import com.vkiprono.springbootrestclient.dtos.RegisterUserRequest;
import com.vkiprono.springbootrestclient.dtos.ResponseTokenDTO;
import org.springframework.http.ResponseEntity;

public interface UserServiceI {
     ResponseEntity<ResponseTokenDTO> authenticate(AuthenticateUserRequest authenticateUserRequest) throws Exception;

     ResponseTokenDTO register(RegisterUserRequest registerUserRequest) throws Exception;

}
