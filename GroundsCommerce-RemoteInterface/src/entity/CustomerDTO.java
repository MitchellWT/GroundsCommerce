/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author mitchell
 */
public class CustomerDTO implements Serializable {
    private final String email;
    private final String name;
    private final String address;
    private final String postcode;
    private final String state;
    private final String country;
    private final String phone;

    public CustomerDTO(String email, String name, String address, String postcode, String state, String country, String phone) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.state = state;
        this.country = country;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }
}
