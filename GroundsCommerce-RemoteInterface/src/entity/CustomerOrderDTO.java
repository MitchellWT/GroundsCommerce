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
public class CustomerOrderDTO implements Serializable {
    private final int id;
    private final String customer_email;
    private final String order_items;
    private final String delivery_address;
    private final String delivery_postcode;
    private final String delievry_state;
    private final String delivery_country;
    private final String payment_method;

    public CustomerOrderDTO(int id, String customer_email, String order_items, String delivery_address, String delivery_postcode, String delievry_state, String delivery_country, String payment_method) {
        this.id = id;
        this.customer_email = customer_email;
        this.order_items = order_items;
        this.delivery_address = delivery_address;
        this.delivery_postcode = delivery_postcode;
        this.delievry_state = delievry_state;
        this.delivery_country = delivery_country;
        this.payment_method = payment_method;
    }

    public int getId() {
        return id;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public String getOrder_items() {
        return order_items;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public String getDelivery_postcode() {
        return delivery_postcode;
    }

    public String getDelievry_state() {
        return delievry_state;
    }

    public String getDelivery_country() {
        return delivery_country;
    }

    public String getPayment_method() {
        return payment_method;
    }
}
