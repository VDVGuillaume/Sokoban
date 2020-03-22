package main;

import java.util.Scanner;
import util.Language;

import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomainController;
import ui.UC1Applicatie;
import ui.UC2Applicatie;
import ui.UC3Applicatie;
import ui.UC4Applicatie;

public class StartUp {
	public static void main(String[] args) {
		ResourceBundle messages;
		DomainController domeincontroller = new DomainController();
		
		Scanner input = new Scanner(System.in);
		String 	username;
		String  password;
		// Selection language
		System.out.println("Choose your language");
		System.out.println("Enter 1 for English");
		System.out.println("Enter 2 for Dutch");
		System.out.println("Enter 3 for French");
		int userSelection = input.nextInt();
		switch(userSelection) {
		case 1:
			Language.setLanguage("en");
			Locale.setDefault(Language.getLanguage());
			break;
		case 2:
			Language.setLanguage("nl");
			Locale.setDefault(Language.getLanguage());
			break;
		case 3:
			Language.setLanguage("fr");
			Locale.setDefault(Language.getLanguage());
			break;
		}
		
		System.out.println(Language.getLanguage());
		messages = ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage());
		System.out.println(messages.getString("StartUp_LogIn"));
		System.out.println(messages.getString("StartUp_Register"));
		System.out.println("3. test UC3");
		System.out.println("4. test UC4");

		userSelection = input.nextInt();
		switch (userSelection) { /*Added selection menu in UC2*/
		case 1:
			UC1Applicatie app = new UC1Applicatie(domeincontroller);
			app.UI();
			break;
		case 2:
			UC2Applicatie app2 = new UC2Applicatie(domeincontroller);
			app2.UI();			
			break;
		case 3:
			UC3Applicatie app3 = new UC3Applicatie(domeincontroller);
			app3.UI();
			break;
		case 4:
			UC4Applicatie app4 = new UC4Applicatie(domeincontroller);
			app4.UI();
			break;
		}
	}
}
