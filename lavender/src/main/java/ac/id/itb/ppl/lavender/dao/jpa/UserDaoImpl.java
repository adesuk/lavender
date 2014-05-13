package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.UserDao;
import ac.id.itb.ppl.lavender.model.Role;
import ac.id.itb.ppl.lavender.model.User;
import ac.id.itb.ppl.lavender.util.RoleType;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author edbert
 */
@Stateless
public class UserDaoImpl extends JpaDao implements UserDao {
    @Override
    public User login(String userId, String password) {
//        if (userId.equals("AW") && password.equals("12345")) {
//            Role role = new Role(RoleType.KOORDINATOR.getName());
//            role.setName("Koordinator");
//            User user = new User("AW", "", "Dr. Alleria Windrunner, ST, MT", role);
//            return user;
//        }
//        
//        return null;
        Query query = em.createQuery(
            "select u from User u where u.userId = :userId and u.password = :password")
            .setParameter("userId", userId)
            .setParameter("password", password);
        User user = (User) query.getSingleResult();
        return user;
    }
}
