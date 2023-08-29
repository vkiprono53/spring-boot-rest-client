package com.vkiprono.springbootrestclient.restcontrollers;

import com.vkiprono.springbootrestclient.dtos.AuthenticateUserRequest;
import com.vkiprono.springbootrestclient.dtos.RegisterUserRequest;
import com.vkiprono.springbootrestclient.dtos.ResponseTokenDTO;
import com.vkiprono.springbootrestclient.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServiceI userServiceI;

    @Autowired
    public UserController(UserServiceI userServiceI) {
        this.userServiceI = userServiceI;
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/register")
    public String register(RegisterUserRequest registerUserRequest) throws Exception{
        logger.info(":::::START UserController.register():::::");
        String response = null;
        ResponseTokenDTO responseTokenDTO = new ResponseTokenDTO();

        if (registerUserRequest != null){
            responseTokenDTO = userServiceI.register(registerUserRequest);
            response = responseTokenDTO.getToken();
        }
        logger.info(":::::EXIT UserController.register():::::");

        return response;
    }

    @PostMapping("/authenticate")
    public ResponseTokenDTO authenticate(AuthenticateUserRequest userRequest) throws Exception{
        logger.info(":::::START UserController.authenticate():::::");

        ResponseTokenDTO response = null;
        ResponseEntity<ResponseTokenDTO> responseTokenDTO = null;
        if (userRequest != null){
            responseTokenDTO = userServiceI.authenticate(userRequest);
            response = responseTokenDTO.getBody();
        }
        logger.info(":::::EXIT UserController.authenticate():::::");
        return response;
    }

}
