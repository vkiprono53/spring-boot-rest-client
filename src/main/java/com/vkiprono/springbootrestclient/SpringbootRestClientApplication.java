package com.vkiprono.springbootrestclient;

import com.vkiprono.springbootrestclient.dtos.AddressDTO;
import com.vkiprono.springbootrestclient.dtos.EmpSaveRequestDTO;
import com.vkiprono.springbootrestclient.dtos.EmployeeDTO;
import com.vkiprono.springbootrestclient.restcontrollers.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringbootRestClientApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringbootRestClientApplication.class, args);

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    }

