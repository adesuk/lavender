package ac.id.itb.ppl.lavender.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author edbert
 */
public final class TipeEksekusi {
    private final Map<Character, String> tipeEkseksusis;
    
    public TipeEksekusi() {
        tipeEkseksusis = new LinkedHashMap<Character, String>(5);
        tipeEkseksusis.put(AllConstants.SEMINAR_TA_1, "Seminar Tugas Akhir 1");
        tipeEkseksusis.put(AllConstants.SEMINAR_TA_2, "Seminar Tugas Akhir 2");
        tipeEkseksusis.put(AllConstants.SIDANG_TA, "Sidang Tugas Akhir");
        tipeEkseksusis.put(AllConstants.SEMINAR_TESIS, "Seminar Tesis");
        tipeEkseksusis.put(AllConstants.SIDANG_TESIS, "Sidang Tesis");
    }
    
    public Map<Character, String> getTipeEksekusis() {
        Map<Character, String> map = new LinkedHashMap<Character, String>(tipeEkseksusis.size());
        map.putAll(tipeEkseksusis);
        return map; // kasihkan copy-annya
    }
}
