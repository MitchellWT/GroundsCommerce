/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groundscommerce.database.setup;

/**
 *
 * @author mitchell
 */
public class DatabaseSetup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db = new Database();
        
        //db.createCustomerTable();
        //db.createProductTable();
        //db.createOrderTable();
        
        db.createAdmin("admin@email.com", "password");
    }
    
}
