package ac.id.itb.ppl.lavender.bean.local;

import ac.id.itb.ppl.lavender.model.User;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface UserLocal {
    User login(String userId, String password);
}
