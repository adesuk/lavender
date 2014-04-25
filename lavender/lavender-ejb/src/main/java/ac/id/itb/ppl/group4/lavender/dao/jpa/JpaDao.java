package ac.id.itb.ppl.group4.lavender.dao.jpa;

import ac.id.itb.ppl.group4.lavender.dao.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Edbert
 */
public abstract class JpaDao<T, K> {
    protected Class<T> entityClass;
    
    @PersistenceContext(unitName = "lavenderPU")
    protected EntityManager em;
    
    public JpaDao() {
//        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
//        entityClass = (Class<E>) genericSuperClass.getActualTypeArguments()[0];
    }
    
    public List<T> findAllDao() {
        StringBuilder sb = new StringBuilder()
            .append("select e from ")
            .append(entityClass.getName())
            .append(" as e");
        Query query = em.createQuery(sb.toString());
        List<T> entities = query.getResultList();
        return entities;
    }
    
    public T findDao(K id) {
        T entity = em.find(entityClass, id);
        return entity;
    }
    
    public void saveDao(T e) {
        em.persist(e);
    }
    
    public T updateDao(T e) {
        return em.merge(e);
    }
    
    public void deleteDao(T e) {
        em.remove(e);
    }
}
