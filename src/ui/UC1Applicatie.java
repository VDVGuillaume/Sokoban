package ui;

import java.util.Scanner;

import domein.DomainController;
import domein.UserRepository;
import exceptions.PasswordException;
import util.Language;

import java.util.Locale;
import java.util.ResourceBundle;

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
		
		System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GiveUsername"));
		username = input.nextLine();
		System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GivePassword"));
		password = input.nextLine();
		
		controller.logIn(username, password);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());		
			UI();		
		}
		finally {
			input.close();
		}
	}	
}
