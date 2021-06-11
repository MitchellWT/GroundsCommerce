/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.CustomerDTO;
import entity.CustomerOrderDTO;
import entity.ProductDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.ejb.EJB;
import session.CustomerFacadeRemote;
import session.ProductFacadeRemote;
import session.ShoppingCartRemote;

/**
 *
 * @author mitchell
 */
@Named(value = "shoppingCartManagedBean")
@SessionScoped
public class ShoppingCartManagedBean implements Serializable {
    
    @EJB
    private ShoppingCartRemote shoppingCartFacade;
    @EJB
    private ProductFacadeRemote productFacade;
    @EJB
    private CustomerFacadeRemote customerFacade;

    private String email;
    private String delivery_address;
    private String delivery_postcode;
    private String delivery_state;
    private String delivery_country;
    private String payment_method;
    
    public ShoppingCartManagedBean() {
        email = null;
        delivery_address = null;
        delivery_postcode = null;
        delivery_state = null;
        delivery_country = null;
        payment_method = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getDelivery_postcode() {
        return delivery_postcode;
    }

    public void setDelivery_postcode(String delivery_postcode) {
        this.delivery_postcode = delivery_postcode;
    }

    public String getDelivery_state() {
        return delivery_state;
    }

    public void setDelivery_state(String delivery_state) {
        this.delivery_state = delivery_state;
    }

    public String getDelivery_country() {
        return delivery_country;
    }

    public void setDelivery_country(String delivery_country) {
        this.delivery_country = delivery_country;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public ProductFacadeRemote getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeRemote productFacade) {
        this.productFacade = productFacade;
    }

    public ShoppingCartRemote getShoppingCartFacade() {
        return shoppingCartFacade;
    }

    public void setShoppingCartFacade(ShoppingCartRemote shoppingCartFacade) {
        this.shoppingCartFacade = shoppingCartFacade;
    }
    
    public String addToCart(int id) {
        if (id == 0) {
            
            return "error";
        }
        
        boolean result = shoppingCartFacade.addToCart(id, 1);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
    
    public HashMap<Integer, Integer> getCart() {    
        return this.shoppingCartFacade.getCart();
    }
    
    public double getTotal() {
        HashMap<Integer, Integer> cart = this.getCart();
        double total = 0;
        
        for (Entry<Integer, Integer> item: cart.entrySet()) {
            
            total += this.productFacade.readProduct(item.getKey()).getPrice()
                        * item.getValue();
        }
        
        return total;
    }
    
    public String getCustomerData(String email) {
        if (email == null) {
            
            return "error";
        }
        
        CustomerDTO customer = customerFacade.readCustomer(email);
        
        if (customer == null) {
            
            return "failure";
        }
        
        this.email = customer.getEmail();
        this.delivery_address = customer.getAddress();
        this.delivery_postcode = customer.getPostcode();
        this.delivery_state = customer.getState();
        this.delivery_country = customer.getCountry();
        
        return "success";
    }
    
    public String checkOut() {
        
        switch (payment_method) {
            
            case "1":
                    payment_method = "Payment on Delivery";
                break;
        }
        
        CustomerOrderDTO customerOrder = new CustomerOrderDTO(0, email, null, delivery_address,
            delivery_postcode, delivery_state, delivery_country, payment_method);
        boolean result = this.shoppingCartFacade.checkOut(customerOrder);
        
        if (result) {
            
            this.shoppingCartFacade.clearCart();
            
            return "success";
        }
        
        return "failure";
    }
}
