/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.CustomerDTO;
import entity.CustomerOrder;
import entity.CustomerOrderDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mitchell
 */
@DeclareRoles({"admin", "customer"})
@Stateless
public class CustomerOrderFacade implements CustomerOrderFacadeLocal, CustomerOrderFacadeRemote {

    @PersistenceContext(unitName = "GroundsCommerce-ejbPU")
    private EntityManager em;
    
    @EJB
    private CustomerFacadeLocal customerFacade;
    
    public CustomerOrderFacade() {
        
    }
    
    private void create(CustomerOrder entity) {
        em.persist(entity);
    }
    
    private void edit(CustomerOrder entity) {
        em.merge(entity);
    }
    
    private void remove(CustomerOrder entity) {
        em.remove(entity);
    }
    
    private CustomerOrder find(Object id) {
        return em.find(CustomerOrder.class, id);
    }
    
    @Override
    @RolesAllowed({"customer"})
    public boolean createCustomerOrder(CustomerOrderDTO customerOrder) {
        CustomerOrder checkProduct = this.find(customerOrder.getId());
        
        if (checkProduct != null) {
            
            return false;
        }
        
        CustomerOrder newCustomerOrder = this.DTO2DAO(customerOrder);
        this.create(newCustomerOrder);
        
        return true;
    }
    
    @Override
    @RolesAllowed({"admin", "customer"})
    public CustomerOrderDTO readCustomerOrder(int id) {
        CustomerOrder customerOrder = this.find(id);
        
        if (customerOrder != null) {
            
            return this.DAO2DTO(customerOrder);
        }
        
        return null;
    }
    
    @Override
    public boolean updateCustomerOrder(CustomerOrderDTO customerOrder) {
        CustomerOrder customerOrderCheck = this.find(customerOrder.getId());
        
        if (customerOrderCheck != null) {
            
            this.edit(this.DTO2DAO(customerOrder));
            return true;
        }
        
        return false;
    }
    
    @Override
    @RolesAllowed({"admin"})
    public boolean deleteCustomerOrder(int id) {
        CustomerOrder customerOrder = this.find(id);
        
        if (customerOrder != null) {
            
            this.remove(customerOrder);
            return true;
        }
        
        return false;
    }
    
    public CustomerOrder DTO2DAO(CustomerOrderDTO dto) {
        CustomerOrder dao = new CustomerOrder();
        CustomerDTO customerDTO = this.customerFacade.readCustomer(dto.getCustomer_email());

        dao.setId(dto.getId());
        dao.setCustomerEmail(this.CustomerDTO2DAO(customerDTO));
        dao.setOrderItems(dto.getOrder_items());
        dao.setDeliveryAddress(dto.getDelivery_address());
        dao.setDeliveryPostcode(dto.getDelivery_postcode());
        dao.setDeliveryState(dto.getDelievry_state());
        dao.setDeliveryCountry(dto.getDelivery_country());
        dao.setPaymentMethod(dto.getPayment_method());
        
        return dao;
    }
    
    private Customer CustomerDTO2DAO(CustomerDTO dto) {
        Customer dao = new Customer();
        
        dao.setEmail(dto.getEmail());
        dao.setName(dto.getName());
        dao.setAddress(dto.getAddress());
        dao.setPostcode(dto.getPostcode());
        dao.setState(dto.getState());
        dao.setCountry(dto.getCountry());
        dao.setPhone(dto.getPhone());
        
        return dao;
    }
    
    private CustomerOrderDTO DAO2DTO(CustomerOrder dao) {
        CustomerOrderDTO dto = new CustomerOrderDTO(dao.getId(), dao.getCustomerEmail().getEmail(),
            dao.getOrderItems(), dao.getDeliveryAddress(), dao.getDeliveryPostcode(), 
            dao.getDeliveryState(), dao.getDeliveryCountry(), dao.getPaymentMethod());
        
        return dto;
    }
    
    @Override
    @RolesAllowed({"admin"})
    public ArrayList<CustomerOrderDTO> getAllOrders() {
        List queryList = em.createNamedQuery("CustomerOrder.findAll").getResultList();
        ArrayList<CustomerOrderDTO> arrList = new ArrayList<CustomerOrderDTO>();
        CustomerOrderDTO customerOrder;
        CustomerOrder tempCustomerOrder;
        
        for (Object object: queryList) {
            tempCustomerOrder = ((CustomerOrder) object);            
            customerOrder = new CustomerOrderDTO(tempCustomerOrder.getId(), tempCustomerOrder.getCustomerEmail().getEmail(),
                tempCustomerOrder.getOrderItems(), tempCustomerOrder.getDeliveryAddress(),
                tempCustomerOrder.getDeliveryPostcode(), tempCustomerOrder.getDeliveryState(),
                tempCustomerOrder.getDeliveryCountry(), tempCustomerOrder.getPaymentMethod());
            
            arrList.add(customerOrder);
        }
        
        return arrList;
    }
    
    @Override
    @RolesAllowed({"admin", "customer"})
    public ArrayList<CustomerOrderDTO> getAllOrders4Customer(String email) {
        CustomerDTO customerDTO = this.customerFacade.readCustomer(email);
        Customer customer = this.CustomerDTO2DAO(customerDTO);
        
        List queryList = em.createNamedQuery("CustomerOrder.findByCustomerEmail")
            .setParameter("customerEmail", customer).getResultList();
        ArrayList<CustomerOrderDTO> arrList = new ArrayList<CustomerOrderDTO>();
        CustomerOrderDTO customerOrder;
        CustomerOrder tempCustomerOrder;
        
        for (Object object: queryList) {
            tempCustomerOrder = ((CustomerOrder) object);
            customerOrder = new CustomerOrderDTO(tempCustomerOrder.getId(), tempCustomerOrder.getCustomerEmail().getEmail(),
                tempCustomerOrder.getOrderItems(), tempCustomerOrder.getDeliveryAddress(),
                tempCustomerOrder.getDeliveryPostcode(), tempCustomerOrder.getDeliveryState(),
                tempCustomerOrder.getDeliveryCountry(), tempCustomerOrder.getPaymentMethod());
            
            arrList.add(customerOrder);
        }
        
        return arrList;
    }
}
