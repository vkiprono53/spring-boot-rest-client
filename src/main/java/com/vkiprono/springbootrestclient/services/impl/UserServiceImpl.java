package com.vkiprono.springbootrestclient.services.impl;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vkiprono.springbootrestclient.constants.EmployeeConstant;
import com.vkiprono.springbootrestclient.dtos.AuthenticateUserRequest;
import com.vkiprono.springbootrestclient.dtos.RegisterUserRequest;
import com.vkiprono.springbootrestclient.dtos.ResponseTokenDTO;
import com.vkiprono.springbootrestclient.services.BaseServiceI;
import com.vkiprono.springbootrestclient.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserServiceI {

    private final RestTemplate restTemplate;

    private final BaseServiceI baseServiceI;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, BaseServiceI baseServiceI) {
        this.baseServiceI =  baseServiceI;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<ResponseTokenDTO> authenticate(AuthenticateUserRequest authenticateUserRequest)  throws Exception{
        logger.info(":::::START UserServiceImpl.authenticate():::::");
        ResponseTokenDTO responseTokenDTO = new ResponseTokenDTO();
        String authDtls = "";
        JsonMapper jsonMapper = new JsonMapper();

        HttpHeaders authHeaders = new HttpHeaders();
        HttpEntity<String> authEntity = null;
        ResponseEntity<ResponseTokenDTO>responseEntity = null;

        if (authenticateUserRequest != null){

            authDtls = jsonMapper.writeValueAsString(authenticateUserRequest);

            authHeaders = baseServiceI.getHttpHeader();
            authEntity = new HttpEntity<>(authDtls,authHeaders);

            //Calling API:
            responseEntity = restTemplate.exchange(
                    EmployeeConstant.USER_AUTHENTICATE_ENDPOINT_URL, HttpMethod.POST,
                    authEntity,
                    ResponseTokenDTO.class
            );

        }
        logger.info(":::::END UserServiceImpl.authenticate():::::");

        return responseEntity;
    }

    @Override
    public ResponseTokenDTO register(RegisterUserRequest registerUserRequest)  throws Exception{
        logger.info(":::::START UserServiceImpl.register():::::");

        ResponseEntity<ResponseTokenDTO> registerResponse = null;
        ResponseTokenDTO responseTokenDTO = new ResponseTokenDTO();
        String userDtls = "";
        JsonMapper jsonMapper = new JsonMapper();
        HttpHeaders registerHeaders = null;
        HttpEntity<String> registerEntity = null;

        if (registerUserRequest != null){

            userDtls = jsonMapper.writeValueAsString(registerUserRequest);
            registerHeaders = baseServiceI.getHttpHeader();

            registerEntity = new HttpEntity<>(userDtls, registerHeaders);
            //Calling API:
            registerResponse = restTemplate.exchange(EmployeeConstant.USER_REGISTER_ENDPOINT_URL, HttpMethod.POST,
                    registerEntity, ResponseTokenDTO.class);

            responseTokenDTO = registerResponse.getBody();

        }
        logger.info(":::::END UserServiceImpl.register():::::");

        return responseTokenDTO;
    }


}
