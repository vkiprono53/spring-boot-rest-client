package com.vkiprono.springbootrestclient;

import com.vkiprono.springbootrestclient.dtos.*;
import com.vkiprono.springbootrestclient.enums.Role;
import com.vkiprono.springbootrestclient.restcontrollers.EmployeeController;
import com.vkiprono.springbootrestclient.restcontrollers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCommandLineRunner implements CommandLineRunner {
    private final EmployeeController employeeController;

    private final UserController userController;

    @Autowired
    public EmployeeCommandLineRunner(EmployeeController employeeController, UserController userController) {
        this.employeeController = employeeController;
        this.userController = userController;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---Call Delete Begin---");
        deleteEmployeeByPNo();
        System.out.println("---Call Delete End---");

      /*  System.out.println("---Call Save Begin---");
        saveEmployee();
        System.out.println("---Call Save End---");

         System.out.println("---Call get Begin---");
        getEmployeeByPNo();
        System.out.println("---Call get End---");

        System.out.println("---Call update Begin---");
        updateEmployee();
        System.out.println("---Call update End---");

        System.out.println("---Call Delete Begin---");
        deleteEmployeeByPNo();
        System.out.println("---Call Delete End---");

        System.out.println("---Call registerUser Begin---");
        registerUser();
        System.out.println("---Call registerUser End---");*/


     /*   System.out.println("---Call authenticateUser Begin---");
        authenticateUser();
        System.out.println("---Call authenticateUser End---");*/

   }

    //Save*/
    public  void saveEmployee() throws Exception {

        System.out.println("=======START EmployeeCommandLineRunner.saveEmployee()=======");
        EmpSaveRequestDTO empSaveRequestDTO = new EmpSaveRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setPNo("HRD001");
        employeeDTO.setFirstName("Tanya");
        employeeDTO.setLastName("Becky");
        employeeDTO.setEmail("TanyaBecky@gmail.com");
        employeeDTO.setDepartmentId(1L);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("405, 120");
        addressDTO.setPhone("455");
        addressDTO.setPostalCode("40900");

        empSaveRequestDTO.setEmployeeDTO(employeeDTO);
        empSaveRequestDTO.setAddressDTO(addressDTO);


        ResponseDTO response = employeeController.save(empSaveRequestDTO);
        System.out.println("Status : " + response.getStatus());
        System.out.println("Code : " + response.getCode());
        System.out.println("Message : " + response.getMessage());

        System.out.println("=======END EmployeeCommandLineRunner.saveEmployee()=======");

    }

    //Update
    public  void updateEmployee() throws Exception {

        System.out.println("=======START EmployeeCommandLineRunner.updateEmployee()=======");
        ResponseDTO responseDTO = new ResponseDTO();
        EmpSaveRequestDTO empSaveRequestDTO = new EmpSaveRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setPNo("SYS001");
        employeeDTO.setFirstName("Bran");
        employeeDTO.setLastName("branlee");
        employeeDTO.setEmail("BranLee@gmail.com");
        employeeDTO.setDepartmentId(1L);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("4003");
        addressDTO.setPhone("1545");
        addressDTO.setPostalCode("3094400");

        empSaveRequestDTO.setEmployeeDTO(employeeDTO);
        empSaveRequestDTO.setAddressDTO(addressDTO);


        responseDTO = employeeController.update(empSaveRequestDTO);
        System.out.println("Status : " + responseDTO.getStatus());
        System.out.println("Code : " + responseDTO.getCode());
        System.out.println("Message : " + responseDTO.getMessage());

        System.out.println("=======END EmployeeCommandLineRunner.updateEmployee()=======");

    }

    //get
    public  void getEmployeeByPNo() throws Exception {
        System.out.println("=======START EmployeeCommandLineRunner.getEmployee()=======");

        EmpGetAndDeleteRequestDTO empGetAndDeleteRequestDTO = new EmpGetAndDeleteRequestDTO();

        empGetAndDeleteRequestDTO.setPNo("SYS001");
        EMPGETRESPONSE response = employeeController.getEmployeeByPNo(empGetAndDeleteRequestDTO);
        System.out.println("PNO: " + response.getEmpGetResponseDTO().getEmployeeDtls().getPNo());
        System.out.println("FirstName: " + response.getEmpGetResponseDTO().getEmployeeDtls().getFirstName());
        System.out.println("Status: " + response.getEmpGetResponseDTO().getResponseDTO().getStatus());

        System.out.println("=======END EmployeeCommandLineRunner.getEmployee()=======");

    }
    //delete
    public  void deleteEmployeeByPNo() throws Exception {
        System.out.println("=======START EmployeeCommandLineRunner.deleteEmployeeByPNo()=======");

        EmpGetAndDeleteRequestDTO empGetAndDeleteRequestDTO = new EmpGetAndDeleteRequestDTO();

        empGetAndDeleteRequestDTO.setPNo("HRD001");
        ResponseDTO response = employeeController.deleteEmployeeByPNo(empGetAndDeleteRequestDTO);
        System.out.println("Code: " + response.getCode());
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());

        System.out.println("=======END EmployeeCommandLineRunner.deleteEmployeeByPNo()=======");

    }

    //Register New User"
    public void registerUser() throws Exception{

        System.out.println("=======START EmployeeCommandLineRunner.registerUser()=======");
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("Leon1");
        registerUserRequest.setLastName("Coder1");
        registerUserRequest.setEmail("coder1@gmail.com");
        registerUserRequest.setPassword("code1235");
        registerUserRequest.setRole(Role.ADMIN);

        String response = userController.register(registerUserRequest);

        System.out.println("RegisterUser response is::::::::" + response);

        System.out.println("=======EXIT EmployeeCommandLineRunner.registerUser()=======");

    }

    //Authenticate new user
    public void authenticateUser() throws Exception{

        System.out.println("=======START EmployeeCommandLineRunner.authenticateUser()=======");

        AuthenticateUserRequest userRequest = new AuthenticateUserRequest();
        userRequest.setEmail("coder@gmail.com");
        userRequest.setPassword("code123");

        ResponseTokenDTO response = userController.authenticate(userRequest);

        System.out.println("AuthenticateUser response is::::::::" + response.getToken());

        System.out.println("=======EXIT EmployeeCommandLineRunner.authenticateUser()=======");

    }

}
