package com.skula.myfee.utils;

import java.util.Calendar;

public class DateUtil {

	public static String getDateFormat(String sqliteDate){
		String split[] = sqliteDate.split("-");
		Calendar cal= Calendar.getInstance();
		int year = Integer.valueOf(split[0]);
		int month = Integer.valueOf(split[1]);
		int day = Integer.valueOf(split[2]);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		
		String res ="";

		int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
		switch(dayOfTheWeek){
		case 1:
			res+="Dim. ";
			break;
		case 2:
			res+="Lun. ";
			break;
		case 3:
			res+="Mar. ";
			break;
		case 4:
			res+="Mer. ";
			break;
		case 5:
			res+="Jeu. ";
			break;
		case 6:
			res+="Ven. ";
			break;
		case 7:
			res+="Sam. ";
			break;
		}
		
		res+=""+day;
		
		return res;
	}
}
