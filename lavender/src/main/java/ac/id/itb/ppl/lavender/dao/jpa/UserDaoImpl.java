package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.UserDao;
import ac.id.itb.ppl.lavender.model.User;
import javax.ejb.Stateless;

/**
 *
 * @author edbert
 */
@Stateless
public class UserDaoImpl extends JpaDao implements UserDao {
    @Override
    public User login(String userId, String password) {
        if (userId.equals("AW") && password.equals("12345")) {
            User user = new User("AW", "", "Dr. Alleria Windrunner, ST, MT", "ROLE_KOORDINATOR");
            return user;
        }
        
        return null;
    }
}
