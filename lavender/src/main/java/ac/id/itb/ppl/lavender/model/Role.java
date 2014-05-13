package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author edbert
 */
@Entity
@Table(name = "ROLE")
public class Role {
    
    private static final long serialVersionUID = -999123481283480550L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROLE")
    private String id;
    @Column(name = "NAME_ROLE")
    private String name;
    @Column(name = "DESCRIPTION_ROLE")
    private String description;
    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<User>(0);
    
    public Role() {
    }
    
    public Role(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
