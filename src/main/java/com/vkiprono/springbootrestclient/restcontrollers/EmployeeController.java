package com.vkiprono.springbootrestclient.restcontrollers;

import com.vkiprono.springbootrestclient.dtos.EMPGETRESPONSE;
import com.vkiprono.springbootrestclient.dtos.EmpGetAndDeleteRequestDTO;
import com.vkiprono.springbootrestclient.dtos.EmpSaveRequestDTO;
import com.vkiprono.springbootrestclient.dtos.ResponseDTO;
import com.vkiprono.springbootrestclient.services.EmployeeServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeServiceI employeeServiceI;

    @Autowired
    public EmployeeController(EmployeeServiceI employeeServiceI) {
        this.employeeServiceI = employeeServiceI;
    }

    //Save
    @PostMapping
    public ResponseDTO  save(@RequestBody EmpSaveRequestDTO empSaveRequestDTO) throws Exception {

        logger.info(":::::START EmployeeController.save():::::");
        ResponseDTO response = new ResponseDTO();

        try {
            response =employeeServiceI.saveEmployee(empSaveRequestDTO);

        }
        catch (Exception e){
            logger.info(":::::Exception occurred in EmployeeRestController.save():::::");
        }

        logger.info(":::::END EmployeeRestController.save():::::");

        return response;
    }
    //Save
    @PutMapping
    public ResponseDTO update(@RequestBody EmpSaveRequestDTO empSaveRequestDTO) throws Exception {

        logger.info(":::::START EmployeeController.update():::::");
        ResponseDTO response = new ResponseDTO();

        try {
            response =employeeServiceI.updateEmployee(empSaveRequestDTO);

        }
        catch (Exception e){
            logger.info(":::::Exception occurred in EmployeeRestController.save():::::");

        }

        logger.info(":::::END EmployeeRestController.save():::::");

        return response;
    }
    //Get
    @GetMapping
    public EMPGETRESPONSE getEmployeeByPNo(@RequestBody EmpGetAndDeleteRequestDTO emprequestsave) throws Exception {

        logger.info(":::::START EmployeeController.getEmployeeByPNo():::::");
        EMPGETRESPONSE empgetresponse = new EMPGETRESPONSE();

        try {
            empgetresponse =employeeServiceI.findEmployeeByPNo(emprequestsave);

        }
        catch (Exception e){
            logger.info(":::::Exception occurred in EmployeeRestController.getEmployeeByPNo():::::");

        }
        logger.info(":::::END EmployeeRestController.getEmployeeByPNo():::::");

        return empgetresponse;
    }
    //Get
    @DeleteMapping
    public ResponseDTO deleteEmployeeByPNo(@RequestBody EmpGetAndDeleteRequestDTO emprequestsave) throws Exception {

        logger.info(":::::START EmployeeController.deleteEmployeeByPNo():::::");
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO =employeeServiceI.deleteEmployeeByPNo(emprequestsave);

        }
        catch (Exception e){
            logger.info(":::::Exception occurred in EmployeeRestController.deleteEmployeeByPNo():::::");

        }
        logger.info(":::::END EmployeeRestController.deleteEmployeeByPNo():::::");

        return responseDTO;
    }

}
