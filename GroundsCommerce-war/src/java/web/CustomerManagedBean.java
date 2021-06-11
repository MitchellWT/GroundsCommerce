/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.CustomerDTO;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import session.CustomerFacadeRemote;

/**
 *
 * @author mitchell
 */
@Named(value = "customerManagedBean")
@ConversationScoped
public class CustomerManagedBean implements Serializable {
    @Inject
    private Conversation conversation;
    @EJB
    private CustomerFacadeRemote customerFacade;
    
    private String email;
    private String name;
    private String password;
    private String cPassword;
    private String address;
    private String postcode;
    private String state;
    private String country;
    private String phone;
    
    public CustomerManagedBean() {
        email = null;
        name = null;
        password = null;
        cPassword = null;
        address = null;
        postcode = null;
        state = null;
        country = null;
        phone = null;
    }
    
    public CustomerFacadeRemote getCustomerFacade() {
        return customerFacade;
    }
    
    public void setCustomerFacade(CustomerFacadeRemote customerFacade) {
        this.customerFacade = customerFacade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }
    
    public String addCustomer() {
        
        if (email == null) {
            
            return "error";
        }
        
        CustomerDTO customer = new CustomerDTO(email, name, address,
                postcode, state, country, phone);
        boolean result = customerFacade.createCustomer(customer);
        result = customerFacade.updatePassword(email, password);
        
        if (result) {
            
            return "success";
        } 
        
        return "failure";
    }
    
    public String showCustomer() {
        if (email == null) {
            
            return "error";
        }
        
        return setData();
    }
    
    public String setData() {
        if (email == null) {
            
            return "error";
        }
        
        CustomerDTO customer = customerFacade.readCustomer(email);
        
        if (customer == null) {
            
            return "failure";
        }
        
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.postcode = customer.getPostcode();
        this.state = customer.getState();
        this.country = customer.getCountry();
        this.phone = customer.getPhone();
        
        return "success";
    }
    
    public String setData(String email) {
        if (email == null) {
            
            return "error";
        }
        
        CustomerDTO customer = customerFacade.readCustomer(email);
        
        if (customer == null) {
            
            return "failure";
        }
        
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.postcode = customer.getPostcode();
        this.state = customer.getState();
        this.country = customer.getCountry();
        this.phone = customer.getPhone();
        
        return "success";
    }
    
    public String setDataView(String email) {
        if (email == null) {
            
            return "error";
        }
        
        CustomerDTO customer = customerFacade.readCustomer(email);
        
        if (customer == null) {
            
            return "failure";
        }
        
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.postcode = customer.getPostcode();
        this.state = customer.getState();
        this.country = customer.getCountry();
        this.phone = customer.getPhone();
        
        return "success";
    }
    
    public String updatePassword() {
        if (email == null) {
            
            return "error";
        }
        
        boolean result = customerFacade.updatePassword(email, password);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
    
    public String updatePassword(String email) {
        if (email == null) {
            
            return "error";
        }
        
        boolean result = customerFacade.updatePassword(email, password);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
    
    public String updateCustomer() {
        if (email == null) {
            
            return "error";
        }
        
        CustomerDTO customer = new CustomerDTO(email, name, address,
                postcode, state, country, phone);
        boolean result = customerFacade.updateCustomer(customer);
        
        if (result) {
            
            return "success";
        } 
        
        return "failure";
    }
    
    public String deleteCustomer() {
        if (email == null) {
            
            return "error";
        }
        
        boolean result = customerFacade.deleteCustomer(email);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
}
