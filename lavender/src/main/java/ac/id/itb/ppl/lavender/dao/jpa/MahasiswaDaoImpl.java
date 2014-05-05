package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.MahasiswaDao;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edbert
 */
@Stateless
public class MahasiswaDaoImpl extends JpaDao implements MahasiswaDao {
    @Override
    public Mahasiswa find(String nim) {
        return em.find(Mahasiswa.class, nim);
    }
}
