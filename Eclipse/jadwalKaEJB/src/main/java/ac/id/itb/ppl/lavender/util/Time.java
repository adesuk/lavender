package ac.id.itb.ppl.lavender.util;

import java.util.Calendar;

import ac.id.itb.ppl.lavender.model.SlotWaktu;

public class Time {
	
	public static final String getDayName(int day) {	
		switch (day) {
			case Calendar.MONDAY: return "Senin";
			case Calendar.TUESDAY: return "Selasa";
			case Calendar.WEDNESDAY: return "Rabu";
			case Calendar.THURSDAY: return "Kamis";
			case Calendar.FRIDAY: return "Jumat";
			default: return "n/a";
		}
	}
	
//	public static final String getTime(SlotWaktu waktu) {	
//		StringBuilder sb = new StringBuilder();
//		sb.append(waktu.getJamAwal().g)
//		
//	}
}
