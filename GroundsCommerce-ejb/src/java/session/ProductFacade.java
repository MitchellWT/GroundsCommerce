/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import entity.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mitchell
 */
@DeclareRoles({"admin", "customer"})
@Stateless
public class ProductFacade implements ProductFacadeRemote {

    @PersistenceContext(unitName = "GroundsCommerce-ejbPU")
    private EntityManager em;
    
    public ProductFacade() {
        
    }
    
    private void create(Product entity) {
        em.persist(entity);
    }
    
    private void edit(Product entity) {
        em.merge(entity);
    }
    
    private void remove(Product entity) {
        em.remove(entity);
    }
    
    private Product find(Object id) {
        return em.find(Product.class, id);
    }
  
    @Override
    @RolesAllowed({"admin"})
    public boolean createProduct(ProductDTO product) {
        Product checkProduct = this.find(product.getId());
        
        if (checkProduct != null) {
            
            return false;
        }
        
        Product newProduct = this.DTO2DAO(product);
        this.create(newProduct);
        
        return true;
    }
    
    @Override
    @RolesAllowed({"admin", "customer"})
    public ProductDTO readProduct(int id) {
        Product product = this.find(id);
        
        if (product != null) {
            
            return this.DAO2DTO(product);
        }
        
        return null;
    }
    
    @Override
    @RolesAllowed({"admin"})
    public boolean updateProduct(ProductDTO product) {
        Product productCheck = this.find(product.getId());
        
        if (productCheck != null) {
            
            this.edit(this.DTO2DAO(product));
            return true;
        }
        
        return false;
    }
    
    @Override
    @RolesAllowed({"admin"})
    public boolean deleteProduct(int id) {
        Product product = this.find(id);
        
        if (product != null) {
            
            this.remove(product);
            return true;
        }
        
        return false;
    }
    
    public Product DTO2DAO(ProductDTO dto) {
        Product dao = new Product();
        
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setDescription(dto.getDescription());
        dao.setImage(dto.getImage());
        dao.setPrice(dto.getPrice());
        
        return dao;
    }
    
    private ProductDTO DAO2DTO(Product dao) {
        ProductDTO dto = new ProductDTO(dao.getId(), dao.getName(),
            dao.getDescription(), dao.getImage(), dao.getPrice());
        
        return dto;
    }
    
    @Override
    @RolesAllowed({"admin", "customer"})
    public ArrayList<ProductDTO> getCatalog() {
        List queryList = em.createNamedQuery("Product.findAll").getResultList();
        ArrayList<ProductDTO> arrList = new ArrayList<ProductDTO>();
        ProductDTO product;
        Product tempProduct;
        
        for (Object object: queryList) {
            tempProduct = ((Product) object);
            product = new ProductDTO(tempProduct.getId(), tempProduct.getName(),
                tempProduct.getDescription(), tempProduct.getImage(), tempProduct.getPrice());
            
            arrList.add(product);
        }
        
        return arrList;
    }
}
