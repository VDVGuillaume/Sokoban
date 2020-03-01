package ui;

import java.util.Scanner;

import domein.DomainController;
import domein.UserMapper;
import exceptions.PasswordException;

public class UC2Applicatie {
	private DomainController controller;
		
		public UC2Applicatie(DomainController controller) {
			this.controller = controller;
		}
		
		public void UI() {
			
				
		Scanner input = new Scanner(System.in);
		String 	username;
		String  password;
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
			
		
		}
		
		
		
	
}
