package ui;

import java.util.Scanner;

import data.UserCatalog;
import domein.DomainController;
import exceptions.PasswordException;


public class UC1Applicatie {
	public void UI() {
		
		

	DomainController controller = new DomainController();	
	Scanner input = new Scanner(System.in);
	
	String 	username;
	String  password;
	
	try {
	
	System.out.print("Give username: ");	
	username = input.nextLine();
	System.out.print("Give password: ");
	password = input.nextLine();
	
	controller.logIn(username, password);
	
	}catch (Exception e) {
		System.out.printf("%s%n",e.getMessage());		
		UI();		
	}
	
	}	
}
