package com.skula.myfee.utils;

import java.util.Calendar;

public class DateUtil {

	public static String getDateFormat(String sqliteDate){
		String split[] = sqliteDate.split("-");
		Calendar cal= Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(split[0]));
		cal.set(Calendar.MONTH, Integer.valueOf(split[1])-1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(split[2]));
		
		
		return null;
	}
}
