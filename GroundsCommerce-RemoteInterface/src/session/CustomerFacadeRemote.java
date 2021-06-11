/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerDTO;
import javax.ejb.Remote;

/**
 *
 * @author mitchell
 */
@Remote
public interface CustomerFacadeRemote {
    
    boolean checkCustomer(String email);
    
    boolean createCustomer(CustomerDTO customer);
    
    CustomerDTO readCustomer(String email);
    
    boolean updateCustomer(CustomerDTO customer);
    
    boolean updatePassword(String email, String pass);
    
    boolean deleteCustomer(String email);
}
