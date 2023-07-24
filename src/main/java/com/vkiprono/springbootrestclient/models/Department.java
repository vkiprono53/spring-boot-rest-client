package com.vkiprono.springbootrestclient.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author vkiprono
 * @created 7/10/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "departments")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq",  allocationSize = 1)
    private Long departmentId;
    private String name;
    private String description;

}
