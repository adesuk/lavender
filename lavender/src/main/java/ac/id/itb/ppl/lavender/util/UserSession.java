package ac.id.itb.ppl.lavender.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edbert
 */
public class UserSession {
    private static UserSession userSession = new UserSession();
    
    public static UserSession getInstance() {
        return userSession;
    }
    
    public HttpSession getSession() {
        return (HttpSession) FacesContext
            .getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext
            .getCurrentInstance().getExternalContext().getRequest();
    }
    
    public String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
        return session.getAttribute("userName").toString();
    }
    
    public String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userId");
        } else {
            return null;
        }
    }
}
