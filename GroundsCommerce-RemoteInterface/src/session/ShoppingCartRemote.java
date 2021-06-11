/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerOrderDTO;
import entity.ProductDTO;
import java.util.HashMap;
import javax.ejb.Remote;

/**
 *
 * @author mitchell
 */
@Remote
public interface ShoppingCartRemote {
    
    boolean addToCart(int product_id, int quantity);
    
    HashMap<Integer, Integer> getCart();
    
    boolean removeCartItem(int id);
    
    boolean checkOut(CustomerOrderDTO customerOrder);
    
    void clearCart();
}
