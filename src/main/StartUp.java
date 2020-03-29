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
		
		
		System.out.println(domeincontroller.translate("StartUp_LogIn"));
		System.out.println(domeincontroller.translate("StartUp_Register"));

		userSelection = input.nextInt();
		String[] userInfo = new String[2];

		switch (userSelection) { /* Added selection menu in UC2 */
		case 1:
			UC1Applicatie app = new UC1Applicatie(domeincontroller);
			userInfo = app.UI(input);
			break;
		case 2:
			UC2Applicatie app2 = new UC2Applicatie(domeincontroller);
			userInfo = app2.UI(input);
			break;
		}

		while(true) 
		{
			int menuItem = 0;
			
			System.out.println(++menuItem + domeincontroller.translate("Menu_PlayGame"));
			System.out.println(++menuItem + domeincontroller.translate("Menu_Quit"));
			if (userInfo[1].equals("True")) {
				System.out.println(++menuItem + domeincontroller.translate("Menu_CreateNewGame"));
				System.out.println(++menuItem + domeincontroller.translate("Menu_EditGame"));
			}

			selection = input.nextInt();
			if(selection == 1) 
			{
				UC4Applicatie app = new UC4Applicatie(domeincontroller);
				app.UI(input);				
			}else if(selection == 2) 
			{
				break;	
			}	
		}

		input.close();	
	}
	
}
