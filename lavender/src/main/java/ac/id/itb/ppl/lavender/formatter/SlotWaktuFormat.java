package ac.id.itb.ppl.lavender.formatter;

import ac.id.itb.ppl.lavender.model.SlotWaktu;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author edbert
 */
public class SlotWaktuFormat {
    public static String format(SlotWaktu slotWaktu) {
        Locale indonesia = new Locale("in", "ID");
        Date date1 = slotWaktu.getJamAwal();
        Date date2 = slotWaktu.getJamAkhir();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm", indonesia);
        return new StringBuilder()
            .append(sdf1.format(date1))
            .append("-")
            .append(sdf1.format(date2))
            .toString();
    }
}
