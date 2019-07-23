package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
	
	public static String toStringCalendar(Calendar calendar){
		return simpleDateFormat.format(calendar.getTime());
	}
}
