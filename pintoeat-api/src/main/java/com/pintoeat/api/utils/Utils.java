package com.pintoeat.api.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

public class Utils {
	
	public static final String CDR_LOGGER = "CDR";
	public static final String UI_PLM_LOGGER = "UI_PLM";
	public static final String UI_CDR_LOGGER = "UI_CDR";
	public static final String DELIMETER_LOG = "`" ;
	public static final String SUCCESS_CODE = "200" ;
	public static final String SUCCESS_MSG = "OK" ;
	public static final String ERROR_CODE = "500" ;
	public static final String INTERNAL_ERROR = "500" ;
	public static final String UNAUTHORIZATION = "401" ;
	public static final String BAD_REQUEST = "400" ;
	public static final String FORBIDDEN = "403" ;
	public static final String NOT_FOUND = "404" ;
	public static final String METHOD_NOT_ALLOWED = "405" ;
	public static final String REQUEST_TIMEOUT = "408";
	
	final static Logger uiPlmLogger = Logger.getLogger(Utils.UI_PLM_LOGGER);
	final static Logger uiCdrLogger = Logger.getLogger(Utils.UI_CDR_LOGGER);
	
	public static String UUID(){
		return UUID.randomUUID().toString();
	}
	
	public static String printCdrLog(String remoteAddress, String methodName, String requestUri, String statusCode, String statusMessage, long startTime) {
		long finishTime = System.currentTimeMillis();
		long timeElapsed = finishTime - startTime;
		if(statusCode.equals(Utils.INTERNAL_ERROR) && statusMessage == null) {
			statusMessage = "NullPointerException";
		}
		return Utils.DELIMETER_LOG + remoteAddress + Utils.DELIMETER_LOG + methodName + Utils.DELIMETER_LOG 
				+ requestUri + Utils.DELIMETER_LOG + statusCode + Utils.DELIMETER_LOG + statusMessage + Utils.DELIMETER_LOG  + timeElapsed;
	}
	
	public static String printCdrLog(String remoteAddress, String methodName, String requestUri, String statusCode, Exception e, long startTime) {
		String statusMessage = null;
		long finishTime = System.currentTimeMillis();
		long timeElapsed = finishTime - startTime;
		if(statusCode.equals(Utils.INTERNAL_ERROR) && e.getMessage() == null) {
			statusMessage = "NullPointerException";
		}else {
			statusMessage = e.getClass().getSimpleName() + ": " + e.getMessage();
		}
		return Utils.DELIMETER_LOG + remoteAddress + Utils.DELIMETER_LOG + methodName + Utils.DELIMETER_LOG 
				+ requestUri + Utils.DELIMETER_LOG + statusCode + Utils.DELIMETER_LOG + statusMessage + Utils.DELIMETER_LOG  + timeElapsed;
	}
	
	public static void writeLogUIPlm(String statusCode, String logMessage) {
		if(statusCode != null && statusCode.equals(Utils.SUCCESS_CODE)) {
			uiPlmLogger.info(logMessage);
		}else {
			uiPlmLogger.error(logMessage);
		}
	}
	
	public static void writeLogUICdr(String statusCode, String logMessage) {
		if(statusCode != null && statusCode.equals(Utils.SUCCESS_CODE)) {
			uiCdrLogger.info(logMessage);
		}else {
			uiCdrLogger.error(logMessage);
		}
	}

	public static String cleanString(String aString) {

		if (aString == null) return null;
	
		String cleanString = "";
	
		for (int i = 0; i < aString.length(); ++i) {
	
			cleanString += cleanChar(aString.charAt(i));
	
		}
	
		return cleanString;

	}
	
	private static char cleanChar(char aChar) {
	       // 0 - 9
	       for (int i = 48; i < 58; ++i) {
	    	   if (aChar == i) return (char) i;
	       }
	       // 'A' - 'Z'
	       for (int i = 65; i < 91; ++i) {
	    	   if (aChar == i) return (char) i;
	       }
	       // 'a' - 'z'
	       for (int i = 97; i < 123; ++i) {
	    	   if (aChar == i) return (char) i;
	       }
	       // other valid characters
	        switch (aChar) {
	            case '/':
	                return '/';
	            case '\\':
	                return '\\';
	            case '.':
	                return '.';
	            case '-':
	                return '-';
	            case '_':
	                return '_';
	            case ' ':
	                return ' ';
	            case ':':
	                return ':';
	            case '(':
	            	return '(';
	            case ')':
	            	return ')';
	        }
	        return '%';
	    }
	public static  Timestamp convertStringToTimestamp(String time,String format) throws ParseException {
		Timestamp ts = null;
		if (time.length() > 0) {
			DateFormat Dateformat = new SimpleDateFormat(format);
			Date d = Dateformat.parse(time);
			ts = new Timestamp(d.getTime());
		}
		return ts;
	}
	
}
