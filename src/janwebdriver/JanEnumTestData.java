package janwebdriver;

public enum JanEnumTestData {
	 
	USERNAME("74n"), 
	PASSWORD("password"), 
	FIRSTNAME("Jan"), 
	LASTNAME("Ettles"),
	EMAIL("74n@74n.com"),
	NEWPASSWORD("newpassword"), 	
	NEWFIRSTNAME("newJan"), 
	NEWLASTNAME("newEttles"),
	NEWEMAIL("new74n@new74n.com"),
	NOTESLIST_URL("/notes/list"),
	NOTETITLE("some title"),
	NOTENOTE("some note yadda yadda yadda yadda yadda yadda yadda yadda yadda yadda yadda yadda yadda yadda yadda yadda"),
	SEARCH_TERM("something"),
	BASE_URL("https://localhost.ssl:3000"),
	HOME("/"),
	VIEWPROFILE_URL("/profile/view"),
	LOGIN_GET_URL("/login?username=74n&password=password"),
	AUTHENTICATED_URL("/authenticated"),
	LOGOUT_URL("/logout"),
	REGISTRATION_FORM("/registerform"),
	REGISTRATION_SUBMIT("/submitregister"),
	NOTE_FORM("/noteform"),
	NOTE_SUBMIT("/submitnote"),
	LOGIN_FORM("/loginform"),
	LOGIN_SUBMIT("submitlogin"),
	SEARCH_FORM("/searchform"),
	UPDATEPROFILE_FORM("/updateprofileform"),
	CHANGEPASSWORD_FORM("/changepasswordform"),	
	FORGOTTEN_FORM("/forgottenform");
	
	private String data;
 
	private JanEnumTestData(String s) {
		data = s;
	}
 
	public String getValue() {
		return data;
	}
}