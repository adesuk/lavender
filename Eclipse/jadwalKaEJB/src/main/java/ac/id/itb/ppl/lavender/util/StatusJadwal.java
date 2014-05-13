package ac.id.itb.ppl.lavender.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class StatusJadwal {
	private final Map<Integer, String> statusTerlaksana;
	private final Map<Integer, String> statusLulus;
	
	public StatusJadwal() {
		statusTerlaksana = new LinkedHashMap<Integer, String>(2);
		statusTerlaksana.put(AllConstants.BELUM_TERLAKSANA, "Belum Terlaksana");
		statusTerlaksana.put(AllConstants.SUDAH_TERLAKSANA, "Sudah Terlaksana");
		
		statusLulus = new LinkedHashMap<Integer, String>(2);
		statusLulus.put(AllConstants.BELUM_LULUS, "Belum Lulus");
		statusLulus.put(AllConstants.SUDAH_LULUS, "Sudah Lulus");
	}
	
	public Map<Integer, String> getStatusTerlaksana() {
		return statusTerlaksana;
	}

	public Map<Integer, String> getStatusLulus() {
		return statusLulus;
	}

	public String getStatusTerlaksana(int key) {
		return statusTerlaksana.get(key);
	}
	
	public String getStatusLulus(int key) {
		return statusLulus.get(key);
	}
}
