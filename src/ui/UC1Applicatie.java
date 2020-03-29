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
	
	public String[] UI(Scanner input) {
	String 	username;
	String  password;
	int userSelection;
	String[] info = new String[2];
	
		try {
		
		System.out.println(controller.translate("GiveUsername"));
		String tmpText = input.nextLine();
		username = tmpText;
		System.out.println(controller.translate("GivePassword"));
		password = input.nextLine();
		
		controller.logIn(username, password);
		info = controller.getInfoUser();
		System.out.println("gebruikersnaam = " + info[0]);
		}catch (Exception e) {
			e.printStackTrace();		
			info = UI(input);		
		}
		finally {
		}
		
		return info;
	}	
}
