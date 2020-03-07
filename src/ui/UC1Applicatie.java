package ui;

import java.util.Scanner;

import domein.DomainController;
import domein.UserRepository;
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
			
		try {
		
		System.out.println("Give username: ");
		username = input.nextLine();
		System.out.println("Give password: ");
		password = input.nextLine();
		
		controller.logIn(username, password);
		
		}catch (Exception e) {
			System.out.printf("%s%n",e.getMessage());		
			UI();		
		}
		finally {
			input.close();
		}
	}	
}
