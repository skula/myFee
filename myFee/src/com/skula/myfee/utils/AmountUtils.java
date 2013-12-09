package com.skula.myfee.utils;


public class AmountUtils {
	public static String format(String amount){
		return amount.replace(".", ",")+ " €";
	}
}
