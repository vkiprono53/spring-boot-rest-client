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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_seq")
    @SequenceGenerator(name="addresses_seq", sequenceName = "addresses_seq", allocationSize=1)
    private String  address;
    private String phone;
    private String postalCode;
}
