/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerOrderDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author mitchell
 */
@Remote
public interface CustomerOrderFacadeRemote {
    
    ArrayList<CustomerOrderDTO> getAllOrders();
    
    ArrayList<CustomerOrderDTO> getAllOrders4Customer(String email);
    
    boolean deleteCustomerOrder(int id);
}
