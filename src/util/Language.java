package util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Language {
	private String language;
	private ResourceBundle bundle;
	
	public void setLanguage(int userSelection) {
	Locale locale;
	
	switch(userSelection) {
	case 1:
		language="en";
		locale=new Locale(language);
		bundle= ResourceBundle.getBundle("resources.MessagesBundle" , locale);
		break;
	case 2:
		language="nl";
		locale=new Locale(language);
		bundle= ResourceBundle.getBundle("resources.MessagesBundle", locale);
		break;
	case 3:
		language="fr";
		locale=new Locale(language);
		bundle= ResourceBundle.getBundle("resources.MessagesBundle", locale);
		break;
	}
	}
	
	public String getLanguage() {
		return language;	
	}
		
	
	public String translate(String word) {
	String msg = bundle.getString(word);
	return msg;
	}

}
