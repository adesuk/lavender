package ac.id.itb.ppl.group4.lavender.managedbean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named(value = "homeMBean")
@SessionScoped
public class HomeMBean implements java.io.Serializable {
    public String getHello() {
        return "Hello, World!";
    }
    
    public String hello() {
        return "index.xhtml";
    }
}
