package ac.id.itb.ppl.group4.lavender.dao;

import java.util.List;

/**
 *
 * @author Edbert
 * 
 * @param E entitasnya
 * @param K id(key)nya
 */
public interface Dao<E, K> {
    /**
     * Cari semua entitas yang ada di database.
     * 
     * @return Semua entitas
     */
    List<E> findAll();
    
    /**
     * Cari entitas berdasarkan IDnya
     * 
     * @param id
     * @return Entitas yang dicari berdasarkan IDnya
     */
    E find(K id);
    
    /**
     * Simpan entitas
     * 
     * @param e Entitas yang akan disimpan
     */
    void save(E e);
    
    /**
     * Update entitas
     * 
     * @param e Entitas yang akan diupdate
     * @return Entitas yang sudah diupdate
     */
    E update(E e);
    
    /**
     * Delete entitas
     * 
     * @param e Entitas yang akan dihapus
     */
    void delete(E e);
}
