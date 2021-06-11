/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerOrder;
import entity.CustomerOrderDTO;
import javax.ejb.Local;

/**
 *
 * @author mitchell
 */
@Local
public interface CustomerOrderFacadeLocal {
    
    boolean createCustomerOrder(CustomerOrderDTO customerOrder);
    
    CustomerOrderDTO readCustomerOrder(int id);
    
    boolean updateCustomerOrder(CustomerOrderDTO customerOrder);
    
    boolean deleteCustomerOrder(int id);
    
    CustomerOrder DTO2DAO(CustomerOrderDTO dto);
}
