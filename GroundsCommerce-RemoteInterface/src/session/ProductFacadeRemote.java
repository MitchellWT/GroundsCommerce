/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author mitchell
 */
@Remote
public interface ProductFacadeRemote {
    
    boolean createProduct(ProductDTO customer);
    
    ProductDTO readProduct(int id);
    
    boolean updateProduct(ProductDTO customer);
    
    boolean deleteProduct(int id);
    
    ArrayList<ProductDTO> getCatalog();
}
