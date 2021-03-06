package main;


import java.util.Scanner;
import util.Language;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomainController;
import ui.UC1Applicatie;
import ui.UC2Applicatie;
import ui.UC3Applicatie;
import ui.UC4Applicatie;
import ui.UC5Applicatie;
import ui.UC6Applicatie;
import ui.UC7Applicatie;

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
		
		int languageSelection=-1;
		boolean flag=true;
		do {
			try {
				languageSelection = input.nextInt();
				
				if(languageSelection <1|| languageSelection >3) {
					throw new IllegalArgumentException("The choice should be in the interval [1,3].");
				}
				
				flag=false;
			}
			catch(InputMismatchException ex) {
				System.out.println("Please enter an integer.");
				input.nextLine();
			}
			catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
			
		}while(flag);
		
	
		DomainController domeincontroller = new DomainController();
		domeincontroller.setLanguage(languageSelection);
		
		
		System.out.println(domeincontroller.translate("StartUp_LogIn"));
		System.out.println(domeincontroller.translate("StartUp_Register"));

		
		userSelection=-1;
		flag=true;
		String[] userInfo = new String[2];
		
		do {
			try {
				userSelection = input.nextInt();
				if(userSelection <1 || userSelection >2) {
					throw new IllegalArgumentException(domeincontroller.translate("ChoiceBetween").replace("$param1", Integer.toString(1)).replace("$param2", Integer.toString(2)));
				}
				flag=false;
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
				
			}catch(InputMismatchException ex) {
				System.out.println(domeincontroller.translate("GiveIntegerNumber"));
				input.nextLine();
			}catch(IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
			
		}while(flag);
		

		while(true)
		{
			int menuItem = 0;
			
			System.out.println(++menuItem + domeincontroller.translate("Menu_PlayGame"));
			System.out.println(++menuItem + domeincontroller.translate("Menu_Quit"));
			if (userInfo[1].equals("True")) {
				System.out.println(++menuItem + domeincontroller.translate("Menu_CreateNewGame"));
				System.out.println(++menuItem + domeincontroller.translate("Menu_EditGame"));
			}

			selection = -1;
			flag=true;
			do {
				try {
					selection = input.nextInt();
					
					if(userInfo[1].equals("True") && (selection <1 || selection>4)) {
						throw new IllegalArgumentException(domeincontroller.translate("ChoiceBetween").replace("$param1", Integer.toString(1)).replace("$param2", Integer.toString(4)));
					}else if(userInfo[1].equals("False") && (selection <1 || selection>2)) {
						throw new IllegalArgumentException(domeincontroller.translate("ChoiceBetween").replace("$param1", Integer.toString(1)).replace("$param2", Integer.toString(2)));
					}
					flag=false;
					
					if(selection == 1) 
					{
						UC4Applicatie app = new UC4Applicatie(domeincontroller);
						app.UI(input);				
					}else if(selection == 2) 
					{
						System.exit(0);;	
					}	
					else if(selection == 3) {
						UC6Applicatie app = new UC6Applicatie(domeincontroller);
						input.nextLine();
						app.UI(input);	
					}
					else if(selection == 4) {
						UC7Applicatie app = new UC7Applicatie(domeincontroller);
						input.nextLine();
						app.UI(input);	
					}
				}catch(InputMismatchException ex) {
					System.out.println(domeincontroller.translate("GiveIntegerNumber"));
					input.nextLine();
				}catch(IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
				}
			}while(flag);
			
			
		}
	}
	
}
