package com.shivamdev.rxdemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shivamchopra on 18/11/15.
 */
public class Utils {

	public static boolean isValidEmail(String email) {
		String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_STRING);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
