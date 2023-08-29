package com.vkiprono.springbootrestclient.services.impl;

import com.vkiprono.springbootrestclient.dtos.AuthenticateUserRequest;
import com.vkiprono.springbootrestclient.services.BaseServiceI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseServiceI {

    @Value("${auth.username}")
    private String authUser;

    @Value("${auth.password}")
    private String authPassword;

    public HttpHeaders getHttpHeader(){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return httpHeaders;
    }

    //Authentication User:
    @Override
    public AuthenticateUserRequest authUserRequest() {
        AuthenticateUserRequest userRequest = new AuthenticateUserRequest();
        userRequest.setEmail(authUser);
        userRequest.setPassword(authPassword);
        return userRequest;
    }

}
