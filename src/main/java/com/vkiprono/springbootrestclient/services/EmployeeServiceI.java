package com.vkiprono.springbootrestclient.services;

import com.vkiprono.springbootrestclient.dtos.EMPGETRESPONSE;
import com.vkiprono.springbootrestclient.dtos.EmpGetAndDeleteRequestDTO;
import com.vkiprono.springbootrestclient.dtos.EmpSaveRequestDTO;
import com.vkiprono.springbootrestclient.dtos.ResponseDTO;
import org.springframework.stereotype.Service;


public interface EmployeeServiceI {
     ResponseDTO saveEmployee(EmpSaveRequestDTO empSaveRequestDTO) throws Exception;
     ResponseDTO updateEmployee(EmpSaveRequestDTO empSaveRequestDTO) throws Exception;
     EMPGETRESPONSE findEmployeeByPNo(EmpGetAndDeleteRequestDTO emprequestsave) throws Exception;
     ResponseDTO deleteEmployeeByPNo(EmpGetAndDeleteRequestDTO emprequestsave) throws Exception;
}
