package com.vkiprono.springbootrestclient;

import com.vkiprono.springbootrestclient.dtos.*;
import com.vkiprono.springbootrestclient.restcontrollers.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCommandLineRunner implements CommandLineRunner {
    private final EmployeeController employeeController;

    @Autowired
    public EmployeeCommandLineRunner(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---Call Save Begin---");
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


    }

    //Save*/
    public  void saveEmployee() throws Exception {

        System.out.println("=======START EmployeeCommandLineRunner.saveEmployee()=======");
        EmpSaveRequestDTO empSaveRequestDTO = new EmpSaveRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setPNo("DEV001");
        employeeDTO.setFirstName("Chloe");
        employeeDTO.setLastName("Nah");
        employeeDTO.setEmail("chloenah@gmail.com");
        employeeDTO.setDepartmentId(1L);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("200, 200");
        addressDTO.setPhone("222222");
        addressDTO.setPostalCode("900300");

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
        employeeDTO.setFirstName("Brany");
        employeeDTO.setLastName("Lee111");
        employeeDTO.setEmail("leebrany111@gmail.com");
        employeeDTO.setDepartmentId(1L);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("5903");
        addressDTO.setPhone("154");
        addressDTO.setPostalCode("30900");

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

        empGetAndDeleteRequestDTO.setPNo("SYS001");
        ResponseDTO response = employeeController.deleteEmployeeByPNo(empGetAndDeleteRequestDTO);
        System.out.println("Code: " + response.getCode());
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());

        System.out.println("=======END EmployeeCommandLineRunner.deleteEmployeeByPNo()=======");

    }

}
