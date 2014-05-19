package ac.id.itb.ppl.lavender.formatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ac.id.itb.ppl.lavender.model.SlotWaktu;

public class DateFormat {
	
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
	
	public static final String getDateDesc(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		
		return getDayName(cal.get(Calendar.DAY_OF_WEEK))
				.concat(",")
				.concat(sdf.format(date));
		
//				.concat(",")
//				.concat(Integer.toString(cal.get(Calendar.DATE)))
//				.concat("-")
//				.concat(Integer.toString(cal.get(Calendar.MONTH)+1))
//				.concat("-")
//				.concat(Integer.toString(cal.get(Calendar.YEAR)));
	}
	
	public static final String getPukul(SlotWaktu slotWaktu) {	
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(slotWaktu.getJamAwal().getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		return sdf.format(slotWaktu.getJamAwal())
				.concat("-")
				.concat(sdf.format(slotWaktu.getJamAkhir()));
	}
}
