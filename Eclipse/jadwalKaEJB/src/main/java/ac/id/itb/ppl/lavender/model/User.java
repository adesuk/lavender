package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author edbert
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "USER_NAME")
    private String userId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @ManyToOne
    @JoinColumn(name = "ID_ROLE")
    private Role role;
    
    public User() {
    }
    
    public User(String userId) {
        this.userId = userId;
    }
    
    public User(String userId, String password, String name, Role role) {
        this.userId = userId;
        this.password = password;
        this.firstName = name;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
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
