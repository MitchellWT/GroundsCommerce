/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.ProductDTO;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import session.ProductFacadeRemote;

/**
 *
 * @author mitchell
 */
@Named(value = "productManagedBean")
@ConversationScoped
public class ProductManagedBean implements Serializable {
    
    @Inject
    private Conversation conversation;
    @EJB
    private ProductFacadeRemote productFacade;
    
    private int id;
    private String name;
    private String description;
    private String image;
    private double price;
    
    public ProductManagedBean() {
        id = 0;
        name = null;
        description = null;
        image = null;
        price = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public ProductFacadeRemote getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeRemote productFacade) {
        this.productFacade = productFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }
    
    public String addProduct() {
        ProductDTO product = new ProductDTO(id, name, description,
            image, price);
        boolean result = productFacade.createProduct(product);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
    
    public String showProduct() {
        
        return setData();
    }
    
    public String setData() {
        ProductDTO product = productFacade.readProduct(id);
        
        if (product == null) {
            
            return "failure";
        }
        
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.price = product.getPrice();
        
        return "success";
    }
    
    public String updateProduct() {
        ProductDTO product = new ProductDTO(id, name, description,
            image, price);
        boolean result = productFacade.updateProduct(product);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
    
    public String deleteProduct() {
        boolean result = productFacade.deleteProduct(id);
        
        if (result) {
            
            return "success";
        }
        
        return "failure";
    }
}
