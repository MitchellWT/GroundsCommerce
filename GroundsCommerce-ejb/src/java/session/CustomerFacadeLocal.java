/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerDTO;
import javax.ejb.Local;

/**
 *
 * @author mitchell
 */
@Local
public interface CustomerFacadeLocal {
    
    CustomerDTO readCustomer(String email);
}
