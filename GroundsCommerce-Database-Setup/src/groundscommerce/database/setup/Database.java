/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groundscommerce.database.setup;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mitchell
 */
public class Database {
    public static Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String URL = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String user = "APP";
        String pass = "APP";
        
        return DriverManager.getConnection(URL, user, pass);
    }
    
    public void createCustomerTable() {
        Connection connect = null;
        Statement statement = null;
        
        try {
            connect = getConnection();
            statement = connect.createStatement();
            statement.execute("CREATE TABLE Customer("
                + "email VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255),"
                + "name VARCHAR(255),"
                + "address VARCHAR(255),"
                + "postcode VARCHAR(255),"
                + "state VARCHAR(255),"
                + "country VARCHAR(255),"
                + "phone VARCHAR(255),"
                + "role VARCHAR(255),"
                + "CONSTRAINT customer_email PRIMARY KEY(email)"
                + ")");
            
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    
                } catch (SQLException e) {
                
                }
                
            }
            
            if (connect!= null) {
                try {
                    connect.close();
                    
                } catch (SQLException sqlEx) {
                
                }
            }
        }
    }
    
    public void createProductTable() {
        Connection connect = null;
        Statement statement = null;
        
        try {
            connect = getConnection();
            statement = connect.createStatement();
            statement.execute("CREATE TABLE Product("
                + "id INT GENERATED ALWAYS AS IDENTITY,"
                + "name VARCHAR(255),"
                + "description VARCHAR(255),"
                + "image VARCHAR(255),"
                + "price DOUBLE,"
                + "CONSTRAINT product_id PRIMARY KEY(id)"
                + ")");
            
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    
                } catch (SQLException e) {
                
                }
                
            }
            
            if (connect!= null) {
                try {
                    connect.close();
                    
                } catch (SQLException sqlEx) {
                
                }
            }
        }
    }
    
    public void createOrderTable() {
        Connection connect = null;
        Statement statement = null;
        
        try {
            connect = getConnection();
            statement = connect.createStatement();
            statement.execute("CREATE TABLE Customer_Order("
                + "id INT GENERATED ALWAYS AS IDENTITY,"
                + "customer_email VARCHAR(255),"
                + "order_items VARCHAR(255),"
                + "delivery_address VARCHAR(255),"
                + "delivery_postcode VARCHAR(255),"
                + "delivery_state VARCHAR(255),"
                + "delivery_country VARCHAR(255),"
                + "payment_method VARCHAR(255)," 
                + "CONSTRAINT order_id PRIMARY KEY(id),"
                + "CONSTRAINT foreign_customer_email FOREIGN KEY(customer_email) REFERENCES Customer(email)"
                + ")");
            
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    
                } catch (SQLException e) {
                
                }
                
            }
            
            if (connect!= null) {
                try {
                    connect.close();
                    
                } catch (SQLException sqlEx) {
                
                }
            }
        }
    }
    
    public void dropAllTables() {
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute("DROP TABLE Customer");
            statement.execute("DROP TABLE Product");
            statement.execute("DROP TABLE Order");
            statement.execute("DROP TABLE Product_Order_Pivot");
            
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    
                } catch (SQLException e) {
                
                }
            }
            
            if (connection!= null) {
                try {
                    connection.close();
                    
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
    
    public void createAdmin(String email, String password) {
        Connection connect = null;
        Statement statement = null;
        
        try {          
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);

            password = bigInt.toString(16);

        } catch (NoSuchAlgorithmException ex) {
        
            ex.printStackTrace();
        }
        
        try {
            connect = getConnection();
            statement = connect.createStatement();
            statement.execute("INSERT INTO CUSTOMER(EMAIL, PASSWORD, ROLE) VALUES('"
                + email + "','"
                + password + "',"
                + "'admin'"
                + ")");
            
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    
                } catch (SQLException e) {
                
                }
                
            }
            
            if (connect!= null) {
                try {
                    connect.close();
                    
                } catch (SQLException sqlEx) {
                
                }
            }
        }
    }
}
