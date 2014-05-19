package ac.id.itb.ppl.lavender.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author edbert
 */
public final class TipeEksekusi {
//    private final Map<Character, String> tipeEkseksusis;
//    
//    public TipeEksekusi() {
//        tipeEkseksusis = new LinkedHashMap<Character, String>(5);
//        tipeEkseksusis.put(AllConstants.SEMINAR_TA_1, "Seminar Tugas Akhir 1");
//        tipeEkseksusis.put(AllConstants.SEMINAR_TA_2, "Seminar Tugas Akhir 2");
//        tipeEkseksusis.put(AllConstants.SIDANG_TA, "Sidang Tugas Akhir");
//        tipeEkseksusis.put(AllConstants.SEMINAR_TESIS, "Seminar Tesis");
//        tipeEkseksusis.put(AllConstants.SIDANG_TESIS, "Sidang Tesis");
//    }
//    
//    public Map<Character, String> getTipeEksekusis() {
//        Map<Character, String> map = new LinkedHashMap<Character, String>(tipeEkseksusis.size());
//        map.putAll(tipeEkseksusis);
//        return map; // kasihkan copy-annya
//    }
//    
//    public String getTipeEksekusi(Character key) {
//    	return tipeEkseksusis.get(key);
//    }
	
	 private final Map<String, Character> tipeEkseksusis;
	    
	    public TipeEksekusi() {
	        tipeEkseksusis = new LinkedHashMap<String, Character>(5);
	        tipeEkseksusis.put("Proposal", AllConstants.PROPOSAL);
	        tipeEkseksusis.put("Seminar Tugas Akhir 1", AllConstants.SEMINAR_TA_1);
	        tipeEkseksusis.put("Seminar Tugas Akhir 2", AllConstants.SEMINAR_TA_2);
	        tipeEkseksusis.put("Sidang Tugas Akhir", AllConstants.SIDANG_TA);
	        tipeEkseksusis.put("Seminar Tesis", AllConstants.SEMINAR_TESIS);
	        tipeEkseksusis.put("Sidang Tesis", AllConstants.SIDANG_TESIS);
	    }
	    
	    public Map<String, Character> getTipeEksekusis() {
	        Map<String, Character> map = new LinkedHashMap<String, Character>(tipeEkseksusis.size());
	        map.putAll(tipeEkseksusis);
	        return map;
	    }
	    
	    public String getName(Character tipeJadwal) {
	        for (String s : tipeEkseksusis.keySet()) {
	            if (tipeEkseksusis.get(s) == tipeJadwal) {
	                return s;
	            }
	        }
	        return "";
	    }
}
