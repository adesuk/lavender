package ac.id.itb.ppl.lavender.util;

/**
 *
 * @author edbert
 */
public enum RoleType {
    
    KOORDINATOR("ROLE_KOORDINATOR"), DOSEN("ROLE_DOSEN");
    
    private String name;
    
    private RoleType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
