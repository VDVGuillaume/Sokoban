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
		Language language;

		language = new Language();
		language.setLanguage();

		DomainController domeincontroller = new DomainController();

		Scanner input = new Scanner(System.in);
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

		int selection = input.nextInt();

		switch (selection) {
		case 1:
			UC3Applicatie app = new UC3Applicatie(domeincontroller);
			app.UI();
			break;
		}

		input.close();
	}
}
