package ac.id.itb.ppl.lavender.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author edbert
 */
public class VersiFormat {
    public static String format(Date versi) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return "v" + sdf.format(versi);
    }
}
