package com.btm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {
	
	public static String extractIdFromUrl(String url){
		String pattern = "(?<=watch\\?v=|/user/|/channel/|embed\\/)[^#\\&\\?]*";

	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(url);

	    if(matcher.find()){
	        return matcher.group();
	    }
		return null;
	}
}
