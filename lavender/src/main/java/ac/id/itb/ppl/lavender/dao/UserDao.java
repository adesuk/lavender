package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.User;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface UserDao {
    User login(String userId, String password);
}
