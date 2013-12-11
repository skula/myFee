package com.skula.myfee.utils;

import java.util.Calendar;

public class DateUtil {

	public static String getDayCompleteFormat(int day, int month, int year) {
		Calendar cal = Calendar.getInstance();
		int tYear = cal.get(Calendar.YEAR);
		int tMonth = cal.get(Calendar.MONTH) + 1;
		int tDay = cal.get(Calendar.DAY_OF_MONTH);

		if (day == tDay && year == tYear && month == tMonth) {
			return "Aujourd'hui";
		}

		cal.add(Calendar.DAY_OF_MONTH, -1);
		tYear = cal.get(Calendar.YEAR);
		tMonth = cal.get(Calendar.MONTH) + 1;
		tDay = cal.get(Calendar.DAY_OF_MONTH);
		if (day == tDay && year == tYear && month == tMonth) {
			return "Hier";
		}

		String dayStr = year + "-" + month + "-" + day;
		switch (month) {
		case 1:
			return getDaySimpleFormat(dayStr) + " Janvier";
		case 2:
			return getDaySimpleFormat(dayStr) + " Février";
		case 3:
			return getDaySimpleFormat(dayStr) + " Mars";
		case 4:
			return getDaySimpleFormat(dayStr) + " Avril";
		case 5:
			return getDaySimpleFormat(dayStr) + " Mai";
		case 6:
			return getDaySimpleFormat(dayStr) + " Juin";
		case 7:
			return getDaySimpleFormat(dayStr) + " Juillet";
		case 8:
			return getDaySimpleFormat(dayStr) + " Août";
		case 9:
			return getDaySimpleFormat(dayStr) + " Septembre";
		case 10:
			return getDaySimpleFormat(dayStr) + " Octobre";
		case 11:
			return getDaySimpleFormat(dayStr) + " Novembre";
		case 12:
			return getDaySimpleFormat(dayStr) + " Decembre";
		default:
			return getDaySimpleFormat(dayStr);
		}
	}

	public static String getDaySimpleFormat(String sqliteDate) {
		String split[] = sqliteDate.split("-");
		Calendar cal = Calendar.getInstance();
		int year = Integer.valueOf(split[0]);
		int month = Integer.valueOf(split[1]);
		int day = Integer.valueOf(split[2]);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);

		String res = "";

		int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
		switch (dayOfTheWeek) {
		case 1:
			res += "Dim.";
			break;
		case 2:
			res += "Lun.";
			break;
		case 3:
			res += "Mar.";
			break;
		case 4:
			res += "Mer.";
			break;
		case 5:
			res += "Jeu.";
			break;
		case 6:
			res += "Ven.";
			break;
		case 7:
			res += "Sam.";
			break;
		}

		res += " " + day;

		return res;
	}
}
