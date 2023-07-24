package com.vkiprono.springbootrestclient.services.impl;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vkiprono.springbootrestclient.constants.EmployeeConstant;
import com.vkiprono.springbootrestclient.dtos.*;
import com.vkiprono.springbootrestclient.repositories.EmployeeRepository;
import com.vkiprono.springbootrestclient.services.EmployeeServiceI;
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
    private  EmployeeRepository employeeRepository;
    private  RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    public EmployeeServiceImpl() {
    }

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseDTO saveEmployee(EmpSaveRequestDTO empSaveRequestDTO) throws Exception {
        logger.info(":::::START EmployeeController.save():::::");
        String response = "";
        EmployeeDTO employeeDTO = new EmployeeDTO();
        AddressDTO addressDTO = new AddressDTO();
        EMPREQUESTSAVE emprequestsave = new EMPREQUESTSAVE();
        ResponseDTO responseDTO = new ResponseDTO();

        String employeeDetails = "";

        JsonMapper jsonMapper = new JsonMapper();

        try {

            if (empSaveRequestDTO != null){
                employeeDTO = empSaveRequestDTO.getEmployeeDTO();
                addressDTO = empSaveRequestDTO.getAddressDTO();
            }
            //Validation here:

            emprequestsave.setRequestDTO(empSaveRequestDTO);

            employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Call Save API:
            response = restTemplate.postForObject(EmployeeConstant.EMPLOYEE_SAVE_ENDPOINT_URL,employeeDetails, String.class);

            //Map JSON to response class
            responseDTO = jsonMapper.readValue(response, ResponseDTO.class);

        }

        catch (Exception exception){

        }

        return responseDTO;
    }

    @Override
    public ResponseDTO updateEmployee(EmpSaveRequestDTO empSaveRequestDTO) throws Exception {
        logger.info(":::::START EmployeeController.updateEmployee():::::");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        AddressDTO addressDTO = new AddressDTO();
        EMPREQUESTSAVE emprequestsave = new EMPREQUESTSAVE();
        String employeeDetails = "";

        ResponseDTO responseDTO = new ResponseDTO();
        JsonMapper jsonMapper = new JsonMapper();

        try {
            if (empSaveRequestDTO != null){
                employeeDTO = empSaveRequestDTO.getEmployeeDTO();
                addressDTO = empSaveRequestDTO.getAddressDTO();
            }
            //Validation here:
            emprequestsave.setRequestDTO(empSaveRequestDTO);

            employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Call Update API:
            ResponseEntity<String> response  = restTemplate.execute(EmployeeConstant.EMPLOYEE_UPDATE_ENDPOINT_URL,
                    HttpMethod.PUT,
                    restTemplate.httpEntityCallback(emprequestsave),
                    restTemplate.responseEntityExtractor(String.class)
            );

                responseDTO = jsonMapper.readValue(response.getBody(), ResponseDTO.class);
        }

        catch (Exception exception){

        }

        return  responseDTO;
    }

    @Override
    public EMPGETRESPONSE findEmployeeByPNo(EmpGetAndDeleteRequestDTO emprequestsave) throws Exception {
        logger.info(":::::START EmployeeController.save():::::");
        String response = "";
        String employeeDetails = "";
        Map<String, String> params = new HashMap<>();
        EMPGETRESPONSE empgetresponse = new EMPGETRESPONSE();

        JsonMapper jsonMapper = new JsonMapper();

        try {

            if (emprequestsave != null){
                employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

                //Setting parameters:
                params.put("PNo", emprequestsave.getPNo());
            }
            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Call get API:
           response = restTemplate.getForObject(EmployeeConstant.EMPLOYEE_GET_BY_PNO_ENDPOINT_URL, String.class, params);

           //Map json return class
            empgetresponse = jsonMapper.readValue(response, EMPGETRESPONSE.class);

            logger.info("::::::response From server:::::" + empgetresponse);

        }

        catch (Exception exception){

        }

        return empgetresponse;
    }

    @Override
    public ResponseDTO deleteEmployeeByPNo(EmpGetAndDeleteRequestDTO emprequestsave) throws Exception {
        logger.info(":::::START EmployeeController.save():::::");
        ResponseDTO responseDTO = new ResponseDTO();
        String employeeDetails = "";

        JsonMapper jsonMapper = new JsonMapper();

        try {

            if (emprequestsave != null){
                employeeDetails = jsonMapper.writeValueAsString(emprequestsave);

            }
            //Validation here:

            logger.info(":::::JSON to parse is here is:::::" + employeeDetails);

            //Call get API:
            ResponseEntity<String> response  = restTemplate.execute(EmployeeConstant.EMPLOYEE_DELETE_ENDPOINT_URL,
                    HttpMethod.DELETE,
                    restTemplate.httpEntityCallback(emprequestsave),
                    restTemplate.responseEntityExtractor(String.class)
            );

            responseDTO = jsonMapper.readValue(response.getBody(), ResponseDTO.class);

        }

        catch (Exception exception){

        }

        return responseDTO;
    }
}
