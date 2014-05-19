package ac.id.itb.ppl.lavender.formatter;

import ac.id.itb.ppl.lavender.model.Periode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Edbert
 */
public class PeriodeFormat {
    public static String format(Periode periode) {
        Locale indonesia = new Locale("in", "ID");
        Date date1 = periode.getPeriodeAwal();
        Date date2 = periode.getPeriodeAkhir();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM y", indonesia);
        return new StringBuilder()
            .append(sdf1.format(date1))
            .append(" s.d. ")
            .append(sdf1.format(date2))
            .toString();
    }
 
    public static String formatDate(Date date) {
        Locale indonesia = new Locale("in", "ID");
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE, d MMM y", indonesia);
        return sdf1.format(date);
    }
}
