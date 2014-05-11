package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author edbert
 */
@Entity
public class User implements Serializable {
    @Id
    private String userId;
    private String password;
    private String name;
    private String role;
    
    public User() { }
    
    public User(String userId, String password, String name, String role) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof User)) {
            return false;
        }
        
        User o = (User) obj;
        if (o.getUserId() != null && o.getUserId().equals(userId)) {
            return true;
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        return hash;
    }
}
