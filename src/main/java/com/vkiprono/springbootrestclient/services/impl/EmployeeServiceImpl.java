package com.vkiprono.springbootrestclient.services.impl;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vkiprono.springbootrestclient.constants.EmployeeConstant;
import com.vkiprono.springbootrestclient.dtos.*;
import com.vkiprono.springbootrestclient.exceptions.UserNotFoundException;
import com.vkiprono.springbootrestclient.services.BaseServiceI;
import com.vkiprono.springbootrestclient.services.EmployeeServiceI;
import com.vkiprono.springbootrestclient.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeServiceI {
    private final RestTemplate restTemplate;
    private  final UserServiceI userServiceI;

    private final BaseServiceI baseServiceI;

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate, UserServiceI userServiceI, BaseServiceI baseServiceI) {
        this.restTemplate = restTemplate;
        this.userServiceI = userServiceI;
        this.baseServiceI =  baseServiceI;
    }

    @Override
    public ResponseDTO saveEmployee(EmpSaveRequestDTO empSaveRequestDTO) throws Exception {
        logger.info(":::::START EmployeeServiceImpl.save():::::");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        AddressDTO addressDTO = new AddressDTO();
        EMPREQUESTSAVE emprequestsave = new EMPREQUESTSAVE();
        ResponseDTO responseDTO = new ResponseDTO();

        String employeeDetails = "";

        JsonMapper jsonMapper = new JsonMapper();
        StringBuilder bearerToken = new StringBuilder();
        HttpEntity<String> httpEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<ResponseDTO> response = null;

        try {

            //Validation
            if (empSaveRequestDTO != null){
                employeeDTO = empSaveRequestDTO.getEmployeeDTO();
                addressDTO = empSaveRequestDTO.getAddressDTO();
            }

            emprequestsave.setRequestDTO(empSaveRequestDTO);

            employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Auth Before calling API:
            AuthenticateUserRequest userRequest = baseServiceI.authUserRequest();

            ResponseEntity<ResponseTokenDTO> authResponse = userServiceI.authenticate(userRequest);

            if (authResponse.getStatusCode().equals(HttpStatus.OK)){
                //Call Save API:
                if (authResponse.getBody() != null) {
                    bearerToken.append(EmployeeConstant.BEARER).append(authResponse.getBody().getToken());
                }

                 httpHeaders = baseServiceI.getHttpHeader();
                 httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken.toString());

                 httpEntity = new HttpEntity<>(employeeDetails, httpHeaders);

                 response = restTemplate.exchange(EmployeeConstant.EMPLOYEE_SAVE_ENDPOINT_URL,
                         HttpMethod.POST,
                         httpEntity,
                         ResponseDTO.class
                         );

                responseDTO = response.getBody();
            }
            else {
                throw new UserNotFoundException(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
            }

        }

        catch (UserNotFoundException exception){
            responseDTO.setCode(EmployeeConstant.USER_NOT_FOUND_CODE);
            responseDTO.setStatus(EmployeeConstant.STATUS_NOK);
            responseDTO.setMessage(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
        }
        logger.info(":::::END EmployeeServiceImpl.save():::::");

        return responseDTO;
    }

    @Override
    public ResponseDTO updateEmployee(EmpSaveRequestDTO empSaveRequestDTO) throws Exception {
        logger.info(":::::START EmployeeServiceImpl.updateEmployee():::::");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        AddressDTO addressDTO = new AddressDTO();
        EMPREQUESTSAVE emprequestsave = new EMPREQUESTSAVE();
        String employeeDetails = "";

        ResponseDTO responseDTO = new ResponseDTO();
        JsonMapper jsonMapper = new JsonMapper();

        StringBuilder bearerToken = new StringBuilder();
        HttpEntity<String> httpEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<ResponseDTO> response = null;
        AuthenticateUserRequest authenticateUserRequest = null;
        ResponseEntity<ResponseTokenDTO> authResponse = null;

        try {
            if (empSaveRequestDTO != null) {
                employeeDTO = empSaveRequestDTO.getEmployeeDTO();
                addressDTO = empSaveRequestDTO.getAddressDTO();
            }
            //Validation here:
            emprequestsave.setRequestDTO(empSaveRequestDTO);

            employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Authenticate
            authenticateUserRequest = baseServiceI.authUserRequest();

            authResponse = userServiceI.authenticate(authenticateUserRequest);

            if (authResponse.getStatusCode().equals(HttpStatus.OK)) {
                if (authResponse.getBody() != null) {
                    bearerToken.append(EmployeeConstant.BEARER).append(authResponse.getBody().getToken());
                }
                httpHeaders = baseServiceI.getHttpHeader();
                httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken.toString());
                httpEntity = new HttpEntity<>(employeeDetails,httpHeaders);

                //Call Update API:
                response = restTemplate.exchange(EmployeeConstant.EMPLOYEE_UPDATE_ENDPOINT_URL,
                        HttpMethod.PUT,
                        httpEntity,
                        ResponseDTO.class
                );

                responseDTO = response.getBody();

            }
            else {
                throw new UserNotFoundException(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
            }
        }

        catch (UserNotFoundException exception){
            responseDTO.setMessage(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
            responseDTO.setCode(EmployeeConstant.USER_NOT_FOUND_CODE);
            responseDTO.setStatus(EmployeeConstant.STATUS_NOK);
        }
        logger.info(":::::END EmployeeServiceImpl.updateEmployee():::::");

        return  responseDTO;
    }

    @Override
    public EMPGETRESPONSE findEmployeeByPNo(EmpGetAndDeleteRequestDTO emprequestsave) throws Exception {
        logger.info(":::::START EmployeeServiceImpl.findEmployeeByPNo():::::");
        String employeeDetails = "";
        Map<String, String> params = new HashMap<>();
        EMPGETRESPONSE empgetresponse = new EMPGETRESPONSE();

        JsonMapper jsonMapper = new JsonMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = null;
        AuthenticateUserRequest authenticateUserRequest = null;
        ResponseEntity<ResponseTokenDTO> dtoResponseEntity = null;
        StringBuilder bearerToken = new StringBuilder();
        ResponseEntity<EMPGETRESPONSE> response = null;

        try {

            if (emprequestsave != null){
                employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

                //Setting parameters:
                params.put("PNo", emprequestsave.getPNo());
            }
            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Authenticate:
            authenticateUserRequest = baseServiceI.authUserRequest();

            dtoResponseEntity  = userServiceI.authenticate(authenticateUserRequest);

            if (dtoResponseEntity.getStatusCode().equals(HttpStatus.OK)) {

                if (dtoResponseEntity.getBody() != null) {
                    bearerToken.append(EmployeeConstant.BEARER).append(dtoResponseEntity.getBody().getToken());
                }

                httpHeaders = baseServiceI.getHttpHeader();
                httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken.toString());

                httpEntity = new HttpEntity<>(employeeDetails,httpHeaders);

                //Call get API:
                response = restTemplate.exchange(EmployeeConstant.EMPLOYEE_GET_BY_PNO_ENDPOINT_URL,
                        HttpMethod.GET,httpEntity,EMPGETRESPONSE.class, params);

                //Map json return class
                empgetresponse = response.getBody();

            }
            else {
                throw new UserNotFoundException(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
            }
            logger.info("::::::response From server:::::" + empgetresponse);

        }

        catch (UserNotFoundException exception){
            empgetresponse.getEmpGetResponseDTO().getResponseDTO().setStatus(EmployeeConstant.STATUS_NOK);
            empgetresponse.getEmpGetResponseDTO().getResponseDTO().setMessage(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
            empgetresponse.getEmpGetResponseDTO().getResponseDTO().setCode(EmployeeConstant.USER_NOT_FOUND_CODE);

        }

        return empgetresponse;
    }

    @Override
    public ResponseDTO deleteEmployeeByPNo(EmpGetAndDeleteRequestDTO emprequestsave) throws Exception {
        logger.info(":::::START EmployeeController.save():::::");
        ResponseDTO responseDTO = new ResponseDTO();
        String employeeDetails = "";

        JsonMapper jsonMapper = new JsonMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = null;
        AuthenticateUserRequest authenticateUserRequest = null;
        ResponseEntity<ResponseTokenDTO> responseTokenDTO = null;
        StringBuilder bearerToken = new StringBuilder();
        ResponseEntity<ResponseDTO> response = null;

        try {

            if (emprequestsave != null){
                employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

            }

            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Authentication:
            authenticateUserRequest = baseServiceI.authUserRequest();
            responseTokenDTO = userServiceI.authenticate(authenticateUserRequest);

            if (responseTokenDTO.getStatusCode().equals(HttpStatus.OK)) {
                //Call get API:
                if (responseTokenDTO.getBody() != null) {
                    bearerToken.append(EmployeeConstant.BEARER).append(responseTokenDTO.getBody().getToken());
                }
                httpHeaders = baseServiceI.getHttpHeader();
                httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken.toString());
                httpEntity = new HttpEntity<>(employeeDetails, httpHeaders);

                response = restTemplate.exchange(EmployeeConstant.EMPLOYEE_DELETE_ENDPOINT_URL, HttpMethod.DELETE, httpEntity,
                        ResponseDTO.class);

                responseDTO = response.getBody();

            }

            else {
                throw new UserNotFoundException(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
            }

        }

        catch (UserNotFoundException exception){
            responseDTO.setStatus(EmployeeConstant.STATUS_NOK);
            responseDTO.setMessage(EmployeeConstant.USER_NOT_FOUND_CODE_MESSAGE);
            responseDTO.setCode(EmployeeConstant.USER_NOT_FOUND_CODE);

        }

        return responseDTO;
    }
}
