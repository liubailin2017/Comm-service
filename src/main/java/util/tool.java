package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tool {
	  public static boolean isNumericzidai(String str) {
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        if(str == null ||str.equals("")) return false;
	        return pattern.matcher(str).matches();  
	    }
}
