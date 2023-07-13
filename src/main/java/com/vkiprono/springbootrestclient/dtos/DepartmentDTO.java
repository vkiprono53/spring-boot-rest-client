package com.vkiprono.springbootrestclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author vkiprono
 * @created 7/10/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO implements Serializable {

    private Long departmentId;
    private String name;
    private String description;

}
