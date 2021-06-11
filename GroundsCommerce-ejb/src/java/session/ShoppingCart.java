/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerDTO;
import entity.CustomerOrderDTO;
import entity.ProductDTO;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author mitchell
 */
@DeclareRoles({"customer"})
@Stateful
public class ShoppingCart implements ShoppingCartRemote {
    private HashMap<Integer, Integer> cart;
    
    @EJB
    private CustomerOrderFacadeLocal customerOrderFacade;
    
    public ShoppingCart() {
        cart = new HashMap<Integer, Integer>();
    }
    
    @Override
    @RolesAllowed({"customer"})
    public boolean addToCart(int product_id, int quantity) {
        boolean result = false;
        
        try {
            
            if (cart.containsKey(product_id)) {
                
                Integer value;
                value = cart.get(product_id);
                value += quantity;
                
                cart.replace(product_id, value);
                result = true;
                
            } else {
                
                cart.put(product_id, quantity);
                result = true;
            }
            
        } catch (Exception ex) {
            
        }
        
        return result;
    }
    
    @Override
    @RolesAllowed({"customer"})
    public HashMap<Integer, Integer> getCart() {
        return cart;
    }
    
    @Override
    @RolesAllowed({"customer"})
    public boolean removeCartItem(int id) {
        for (Integer product_id: cart.keySet()) {
            
            if (product_id == id) {
                
                cart.remove(product_id);
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    @RolesAllowed({"customer"})
    public boolean checkOut(CustomerOrderDTO customerOrder) {
        if (customerOrder == null) {
            
            return false;
        }
        
        String order_items = "";
        
        for (Entry<Integer, Integer> item: cart.entrySet()) {
            
            order_items += "{" + item.getKey() + "," + item.getValue() + "} ";
        }
        
        order_items = order_items.trim();
        
        CustomerOrderDTO newCustomerOrder = new CustomerOrderDTO(0, customerOrder.getCustomer_email(),
            order_items, customerOrder.getDelivery_address(),
            customerOrder.getDelivery_postcode(), customerOrder.getDelievry_state(),
            customerOrder.getDelivery_country(), customerOrder.getPayment_method());
        
        boolean result = this.customerOrderFacade.createCustomerOrder(newCustomerOrder);
        
        return result;
    }
    
    @Override
    @RolesAllowed({"customer"})
    public void clearCart() {
        this.cart.clear();
    }
}
