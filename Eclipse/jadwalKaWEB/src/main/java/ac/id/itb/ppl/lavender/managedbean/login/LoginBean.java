package ac.id.itb.ppl.lavender.managedbean.login;

import ac.id.itb.ppl.lavender.bean.UserBean;
import ac.id.itb.ppl.lavender.model.Role;
import ac.id.itb.ppl.lavender.model.User;
import ac.id.itb.ppl.lavender.util.UserSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edbert
 */
@ManagedBean
@javax.faces.bean.SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -999281800728913L;
    private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());

    @EJB private UserBean userDao;
    private String userId;
    private String password;
    private User user;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
    	return user.getFirstName();
    }
    
    public User getUser() {
        return user;
    }
    
    public Role getRole() {
        if (user == null) {
            return null;
        }
        
        return user.getRole();
    }

    public String login() {
        User result = userDao.login(userId, password);
                
        if (result != null) {
        	 System.out.println("name : "+ result.getFirstName());
             System.out.println("passowrd : "+ result.getPassword());
             System.out.println("role : "+ result.getRole().getName());
             
             // get Http Session and store username
            HttpSession session = UserSession.getInstance().getSession();
            session.setAttribute("userId", userId);
            result.setPassword(null);
            session.setAttribute("user", result);
            user = result;
            return "index?faces-redirect=true";
        } else {
        	System.out.println("result is NULL");
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
            return "login";
        }
    }

    public void logout() {
        HttpSession session = UserSession.getInstance().getSession();
        session.invalidate();
        this.user = null;
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/" + "index.jsf");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);

        }
        //return "index?faces-redirect=true";
    }
    
    public boolean isLoggedIn() {
        return user != null;
    }
}
