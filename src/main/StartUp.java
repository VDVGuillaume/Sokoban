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
		String username;
		String password;
		int userSelection;
		int selection;
		Language language;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Choose your language");
		System.out.println("Enter 1 for English");
		System.out.println("Enter 2 for Dutch");
		System.out.println("Enter 3 for French");
		int languageSelection = input.nextInt();
	
		DomainController domeincontroller = new DomainController();
		domeincontroller.setLanguage(languageSelection);
		
		
		System.out.println(Language.translate("StartUp_LogIn"));
		System.out.println(Language.translate("StartUp_Register"));

		userSelection = input.nextInt();
		String[] userInfo = new String[2];

		switch (userSelection) { /* Added selection menu in UC2 */
		case 1:
			UC1Applicatie app = new UC1Applicatie(domeincontroller);
			userInfo = app.UI();
			break;
		case 2:
			UC2Applicatie app2 = new UC2Applicatie(domeincontroller);
			userInfo = app2.UI();
			break;
		}

		int menuItem = 0;
		System.out.println(++menuItem + Language.translate("Menu_PlayGame"));
		if (userInfo[1] == "True") {
			System.out.println(++menuItem + Language.translate("Menu_CreateNewGame"));
			System.out.println(++menuItem + Language.translate("Menu_EditGame"));
		}
		System.out.println(++menuItem + Language.translate("Menu_Quit"));

		selection = input.nextInt();

		switch (selection) {
		case 1:
			UC3Applicatie app = new UC3Applicatie(domeincontroller);
			app.UI();
			break;
		}

		input.close();	
	}
	
}
