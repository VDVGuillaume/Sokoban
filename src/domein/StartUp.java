package domein;

import ui.UC1Applicatie;
import ui.UC2Applicatie;

import java.util.Scanner;

public class StartUp {
	
	
	
	public static void main(String[] args) {
		
		DomainController domeincontroller = new DomainController();
		
		Scanner input = new Scanner(System.in);
		String 	username;
		String  password;
		
		System.out.println("Enter 1 to log in");
		System.out.println("Enter 2 to register");
		int userSelection = input.nextInt();
		switch (userSelection) { /*Added selection menu in UC2*/
		case 1:
			
			UC1Applicatie app = new UC1Applicatie(domeincontroller);
			app.UI();
			break;
		case 2:
			
			UC2Applicatie app2 = new UC2Applicatie(domeincontroller);
			app.UI();
			
			break;
		

	}

}
