package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.CustomerOrderDTO;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import session.CustomerOrderFacadeRemote;

/**
 *
 * @author mitchell
 */
@Named(value = "allCustomerOrderManagedBean")
@SessionScoped
public class AllCustomerOrderManagedBean implements Serializable {

    @EJB
    private CustomerOrderFacadeRemote customerOrderFacade;
    
    private ArrayList<CustomerOrderDTO> orders;
    private String email;
    
    public AllCustomerOrderManagedBean() {
        orders = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerOrderFacadeRemote getCustomerOrderFacade() {
        return customerOrderFacade;
    }

    public void setCustomerOrderFacade(CustomerOrderFacadeRemote customerOrderFacade) {
        this.customerOrderFacade = customerOrderFacade;
    }

    public ArrayList<CustomerOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<CustomerOrderDTO> orders) {
        this.orders = orders;
    }
    
    public String setData() {
        ArrayList<CustomerOrderDTO> data = this.customerOrderFacade.getAllOrders();
        
        if (data == null) {
            
            return "failure";
        }
        
        this.orders = data;
        
        return "success";
    }
    
    public String setData4Customer() {
        ArrayList<CustomerOrderDTO> data = this.customerOrderFacade.getAllOrders4Customer(email);
        
        if (data == null) {
            
            return "failure";
        }
        
        this.orders = data;
        
        return "success";
    }
    
    public String setData4Customer(String email) {
        ArrayList<CustomerOrderDTO> data = this.customerOrderFacade.getAllOrders4Customer(email);
        
        if (data == null) {
            
            return "failure";
        }
        
        this.orders = data;
        
        return "success";
    }
    
    public HashMap<Integer, Integer> formatOrderItems(String order_items) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        Pattern patten = Pattern.compile("\\{([^}]*)\\}");
        Matcher matcher = patten.matcher(order_items);
        
        while (matcher.find()) {
            
            String[] item_data = matcher.group(1).split(",");
            
            hashMap.put(Integer.parseInt(item_data[0]), 
                Integer.parseInt(item_data[1]));
        }
        
        return hashMap;
    }
    
    public String deleteOrder(int id) {
        boolean result = this.customerOrderFacade.deleteCustomerOrder(id);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
}
