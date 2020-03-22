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
	
	public String[] UI() {	
	Scanner input = new Scanner(System.in);
	String 	username;
	String  password;
	int userSelection;
	String[] info = new String[0];
	
		try {
		
		System.out.println(Language.translate("GiveUsername"));
		username = input.nextLine();
		System.out.println(Language.translate("GivePassword"));
		password = input.nextLine();
		
		controller.logIn(username, password);
		info = controller.getInfoUser();
		System.out.println("gebruikersnaam = " + info[0]);
		}catch (Exception e) {
			e.printStackTrace();		
			UI();		
		}
		finally {
			input.close();
		}
		
		return info;
	}	
}
