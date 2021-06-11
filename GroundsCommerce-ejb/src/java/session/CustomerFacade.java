/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.CustomerDTO;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mitchell
 */
@Stateless
public class CustomerFacade implements CustomerFacadeRemote, CustomerFacadeLocal {
    
    @PersistenceContext(unitName = "GroundsCommerce-ejbPU")
    private EntityManager em;
    
    public CustomerFacade() {
        
    }
    
    private void create(Customer entity) {
        em.persist(entity);
    }
    
    private void edit(Customer entity) {
        em.merge(entity);
    }
    
    private void remove(Customer entity) {
        em.remove(entity);
    }
    
    private Customer find(Object id) {
        return em.find(Customer.class, id);
    }
    
    @Override
    public boolean checkCustomer(String email) {
        return (this.find(email) != null);
    }
  
    @Override
    public boolean createCustomer(CustomerDTO customer) {
        Customer customerCheck = this.find(customer.getEmail());
        
        if (customerCheck != null) {
            
            return false;
        }
        
        Customer newCustomer = this.DTO2DAO(customer);
        this.create(newCustomer);
        
        return true;
    }
    
    @Override
    public CustomerDTO readCustomer(String email) {
        Customer customer = this.find(email);
        
        if (customer != null) {
            
            return this.DAO2DTO(customer);
        }
        
        return null;
    }
    
    @Override
    public boolean updateCustomer(CustomerDTO customer) {
        Customer customerCheck = this.find(customer.getEmail());
        
        if (customerCheck != null) {
            
            this.edit(this.DTO2DAO(customer));
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean updatePassword(String email, String pass) {
        Customer customerCheck = find(email);
        
        if (customerCheck != null) {
            
            try {
                
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(pass.getBytes(StandardCharsets.UTF_8));
                byte[] digest = md.digest();
                BigInteger bigInt = new BigInteger(1, digest);
                
                pass = bigInt.toString(16);
                
            } catch (NoSuchAlgorithmException ex) {
                
                Logger.getLogger(CustomerFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            customerCheck.setPassword(pass);
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean deleteCustomer(String email) {
        Customer customer = this.find(email);
        
        if (customer != null) {
            
            this.remove(customer);
            return true;
        }
        
        return false;
    }
    
    private Customer DTO2DAO(CustomerDTO dto) {
        Customer dao = new Customer();
        
        dao.setEmail(dto.getEmail());
        dao.setName(dto.getName());
        dao.setAddress(dto.getAddress());
        dao.setPostcode(dto.getPostcode());
        dao.setState(dto.getState());
        dao.setCountry(dto.getCountry());
        dao.setPhone(dto.getPhone());
        dao.setRole("customer");
        
        return dao;
    }
    
    private CustomerDTO DAO2DTO(Customer dao) {
        CustomerDTO dto = new CustomerDTO(dao.getEmail(),
            dao.getName(), dao.getAddress(), dao.getPostcode(), 
            dao.getState(), dao.getCountry(), dao.getPhone());
        
        return dto;
    }
}
