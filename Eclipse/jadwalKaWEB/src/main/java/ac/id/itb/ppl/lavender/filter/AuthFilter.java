package ac.id.itb.ppl.lavender.filter;

import ac.id.itb.ppl.lavender.managedbean.login.LoginBean;
import ac.id.itb.ppl.lavender.model.User;
import ac.id.itb.ppl.lavender.util.RoleType;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edbert
 */
//@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.jsf"})
public class AuthFilter implements Filter {
    private static final Logger LOGGER = Logger
        .getLogger(AuthFilter.class.getName());
    
    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /*@Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        try {
            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            if (reqURI.indexOf("/login.xhtml") >= 0 || 
                reqURI.indexOf("/index.xhtml") >= 0 ||
                (ses != null && ses.getAttribute("userId") != null) ||
                reqURI.indexOf("/public/") >= 0 || 
                reqURI.contains("javax.faces.resource")) {
                chain.doFilter(request, response);
            } else { // user didn't log in but asking for a page that is not allowed so take user to login page
                res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");  // Anonymous user. Redirect to login page
            }
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        } catch (ServletException se) {
            LOGGER.log(Level.SEVERE, null, se);
        }
    } //doFilter*/
    
    @Override
    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        // Get the loginBean from session attribute
        LoginBean loginBean = 
            (LoginBean) ((HttpServletRequest)request)
                .getSession()
                .getAttribute("loginBean");
         
        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        User user = loginBean != null ? loginBean.getUser() : null;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        
        // page yang bisa diakses siapa saja
        if (path.indexOf("/index.jsf") >= 0 || path.indexOf("/login.jsf") >= 0
                || path.indexOf("/public/") >= 0 || 
                path.contains("javax.faces.resource") ) {
            chain.doFilter(request, response);
            return;
        }
        
        if (user != null) {
        	
        	 System.out.println("name in auth : "+ user.getFirstName());
        	 System.out.println("passowrd : "+ user.getPassword());
             System.out.println("role in auth : "+ user.getRole().getName());            
        	
            if ((path.indexOf("/jadwal_karya_akhir/") >= 0 ||
                path.indexOf("/karya_akhir/") >= 0 ||
                path.indexOf("/periode/") >= 0 ||
                path.contains("/hazard/") ||
                    "partial/ajax".equals(req.getHeader("Faces-Request"))) &&
                user.getRole().getId().equals(RoleType.KOORDINATOR.getName())) {
                // ga ada masalah dengan link yg bisa diakses koordinator
                chain.doFilter(request, response);
            } else if ((path.startsWith("/hazard/")) &&
                user.getRole().getId().equals(RoleType.DOSEN.getName())) {
                chain.doFilter(request, response);
            } else { // bermasalah
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            res.sendRedirect(req.getContextPath() + "/login.jsf");  // Anonymous user. Redirect to login page
        }
    }

    @Override
    public void destroy() {
    }
}
