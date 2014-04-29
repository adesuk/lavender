package ac.id.itb.ppl.lavender.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author edbert
 */
public final class TipeEksekusi {
    private final Map<String, Character> tipeEkseksusis;
    
    public TipeEksekusi() {
        tipeEkseksusis = new LinkedHashMap<String, Character>(5);
        tipeEkseksusis.put("Seminar Tugas Akhir 1", AllConstants.SEMINAR_TA_1);
        tipeEkseksusis.put("Seminar Tugas Akhir 2", AllConstants.SEMINAR_TA_2);
        tipeEkseksusis.put("Sidang Tugas Akhir", AllConstants.SIDANG_TA);
        tipeEkseksusis.put("Seminar Tesis", AllConstants.SEMINAR_TESIS);
        tipeEkseksusis.put("Sidang Tesis", AllConstants.SIDANG_TESIS);
    }
    
    public Map<String, Character> getTipeEksekusis() {
        Map<String, Character> map = new LinkedHashMap<String, Character>(tipeEkseksusis.size());
        map.putAll(tipeEkseksusis);
        return map; // kasihkan copy-annya
    }
}
