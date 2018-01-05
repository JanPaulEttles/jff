package janwebdriver;

import java.util.HashMap;
import java.util.Map;

public class Helpers {
	
	//String EVIL_PAYLOAD = "<script>alert('74n')</script>";
	public final static String  EVIL_PAYLOAD = "<>()'/ script alert";

	public static String replaceToken(String s, String f, String r) {
		return s.replace("{" + f + "}", r);
		
	}
	public static Map<String, String> populateRegistration() {
		
		Map<String, String> map = new HashMap<String, String>();
		
	    map.put("username", JanEnumTestData.USERNAME.getValue());  
	    map.put("password", JanEnumTestData.PASSWORD.getValue());  
	    map.put("email", JanEnumTestData.EMAIL.getValue());  
	    map.put("firstname", JanEnumTestData.FIRSTNAME.getValue());  
	    map.put("lastname", JanEnumTestData.LASTNAME.getValue()); 
	    
	    return map;
	}

	public static Map<String, String> populateProfile() {
		
		Map<String, String> map = new HashMap<String, String>();
		
	    map.put("email", JanEnumTestData.EMAIL.getValue());  
	    map.put("firstname", JanEnumTestData.FIRSTNAME.getValue());  
	    map.put("lastname", JanEnumTestData.LASTNAME.getValue()); 
	    
	    return map;
	}

	public static Map<String, String> populateSearch() {
		
		Map<String, String> map = new HashMap<String, String>();
		
	    map.put("search", JanEnumTestData.SEARCH_TERM.getValue());  
	    
	    return map;
	}

}
