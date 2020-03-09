package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

	static Locale aLocale;
	
	public static void setLanguage(String language) {
	aLocale = new Locale(language);	
	}
	
	public static Locale getLanguage() {
		return aLocale;	
		}
		
	
	public String translate(String word) {
	ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle",aLocale);
	String msg = bundle.getString(word);
	return msg;
	}

}
