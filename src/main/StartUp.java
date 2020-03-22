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
		String 	username;
		String  password;
		int userSelection;
		
		
		
		DomainController domeincontroller = new DomainController();
		
		Scanner input = new Scanner(System.in);
		System.out.println(Language.translate("StartUp_LogIn"));
		System.out.println(Language.translate("StartUp_Register"));
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
		input.close();
	}
}
