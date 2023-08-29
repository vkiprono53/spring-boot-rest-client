package com.vkiprono.springbootrestclient.services;

import com.vkiprono.springbootrestclient.dtos.AuthenticateUserRequest;
import org.springframework.http.HttpHeaders;

public interface BaseServiceI {
     HttpHeaders getHttpHeader();

     AuthenticateUserRequest authUserRequest();
}
