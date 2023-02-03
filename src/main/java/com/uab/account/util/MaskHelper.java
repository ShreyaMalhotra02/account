package com.uab.account.util;

public class MaskHelper {
	
	public static final String DEFAULT_MASK = "xxxxxxxxx####";

	public static String maskNumber(String number) {
		 
	      int index = 0;
	      StringBuilder masked = new StringBuilder();
	      for (int i = 0; i < DEFAULT_MASK.length(); i++) {
	         char c = DEFAULT_MASK.charAt(i);
	         if (c == '#') {
	            masked.append(number.charAt(index));
	            index++;
	         } else if (c == 'x') {
	            masked.append(c);
	            index++;
	         } else {
	            masked.append(c);
	         }
	      }
	      return masked.toString();
	   }
}
