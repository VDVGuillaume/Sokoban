package util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Language {
	static String language;
	static ResourceBundle bundle;
	
	public void setLanguage() {
	Scanner input = new Scanner(System.in);
	Locale locale;
	System.out.println("Choose your language");
	System.out.println("Enter 1 for English");
	System.out.println("Enter 2 for Dutch");
	System.out.println("Enter 3 for French");
	int userSelection = input.nextInt();
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
	
	public static String getLanguage() {
		return language;	
	}
		
	
	public static String translate(String word) {
	String msg = bundle.getString(word);
	return msg;
	}

}
