/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.ProductDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import session.ProductFacadeRemote;

/**
 *
 * @author mitchell
 */
@Named(value = "allProductManagedBean")
@SessionScoped
public class AllProductManagedBean implements Serializable {
    
    @EJB
    private ProductFacadeRemote productFacade;
    
    private ArrayList<ProductDTO> catalog;
    
    public AllProductManagedBean() {
        catalog = null;
    }   

    public ProductFacadeRemote getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeRemote productFacade) {
        this.productFacade = productFacade;
    }

    public ArrayList<ProductDTO> getCatalog() {
        return catalog;
    }

    public void setCatalog(ArrayList<ProductDTO> catalog) {
        this.catalog = catalog;
    }
    
    public String setData() {
        ArrayList<ProductDTO> data = this.productFacade.getCatalog();
        
        if (data == null) {
            
            return "failure";
        }
        
        this.catalog = data;
        
        return "success";
    }
    
    public ProductDTO getProduct(int id) {
       ProductDTO product = this.productFacade.readProduct(id);
       
       return product;
    }
    
    public String deleteProduct(int id) {
        boolean result = this.productFacade.deleteProduct(id);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
}
