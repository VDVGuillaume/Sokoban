package ui;

import java.util.Scanner;
import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomainController;
import domein.UserRepository;
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

				System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GiveName"));			
				name = input.nextLine();				
				System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GiveFirstName"));
				firstName = input.nextLine();
				System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GiveUsername"));
				username = input.nextLine();
				System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GivePassword"));
				password = input.nextLine();
			
				controller.register(name, firstName, username, password);				
				
				String[] info = controller.getInfoUser();
				System.out.println("gebruikersnaam = " + info[0]);
				Menu.menu(info[1]);

			
			}catch (Exception e) {
				System.out.println(e.getMessage());
				UI();
			}
			finally {
				input.close();
			}
		}
}