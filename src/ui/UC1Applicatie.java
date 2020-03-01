package ui;

import java.util.Scanner;

import domein.DomainController;
import domein.UserMapper;
import exceptions.PasswordException;


public class UC1Applicatie {
	
	private DomainController controller;
	
	public UC1Applicatie(DomainController controller) {
		this.controller = controller;
	}
	
	public void UI() {
		
			
	Scanner input = new Scanner(System.in);
	String 	username;
	String  password;
	
	System.out.println("Enter 1 to log in");
	System.out.println("Enter 2 to register");
	int userSelection = input.nextInt();
	switch (userSelection) { /*Added selection menu in UC2*/
	case 1:

		
		try {
		
		System.out.println("Give username: ");
		input.nextLine(); //empty scanner method required to make selection screen work
		username = input.nextLine();
		System.out.println("Give password: ");
		password = input.nextLine();
		
		controller.logIn(username, password);
		
		}catch (Exception e) {
			System.out.printf("%s%n",e.getMessage());		
			UI();		
		}
		break;
	case 2:
		String 	firstName;
		String 	name;
		
		try {
		System.out.println("Give name: ");
		input.nextLine(); //empty scanner method required to make selection screen work
		name = input.nextLine();
		
		System.out.println("Give firstName: ");
		firstName = input.nextLine();
		System.out.println("Give username: ");
		username = input.nextLine();
		System.out.println("Give password: ");
		password = input.nextLine();

		
		controller.register(name, firstName, username, password);
		controller.logIn(username, password);
		
		}catch (Exception e) {
			System.out.printf("%s%n",e.getMessage());
			UI();
		}
		
		break;
	}
	
	
	
	}	
}
