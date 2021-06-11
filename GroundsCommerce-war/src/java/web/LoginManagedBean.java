/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mitchell
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    private static final String LOGOUT = "logout";
    /**
     * Creates a new instance of MyLoginManagedBean
     */
    public LoginManagedBean() {
    }
    
    public String getLoginName() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        
        return request.getUserPrincipal().getName();
    }

    public String logoutResult() {
        // terminate the session by invalidating the session context
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
          
            request.logout();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // terminate the session by invalidating the session context
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        // terminate the user's login credentials
        return LOGOUT;
    }    
}
