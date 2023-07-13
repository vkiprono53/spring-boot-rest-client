package com.vkiprono.springbootrestclient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AddressDTO implements Serializable {
    @JsonProperty("address")
    private String  address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("postalCode")
    private String postalCode;
}
