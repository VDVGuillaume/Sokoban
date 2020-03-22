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
	int userSelection;
			
		try {
		
		System.out.println(Language.translate("GiveUsername"));
		username = input.nextLine();
		System.out.println(Language.translate("GivePassword"));
		password = input.nextLine();
		
		controller.logIn(username, password);
		String[] info = controller.getInfoUser();
		System.out.println("gebruikersnaam = " + info[0]);
		userSelection = Menu.menu(info[1]);
		
		switch(userSelection) {
		case 1:
			UC3Applicatie UC3applicatie= new UC3Applicatie(controller);
			UC3applicatie.UI();
			break;
		}
		
		
		
		}catch (Exception e) {
			e.printStackTrace();		
			UI();		
		}
		finally {
			input.close();
		}
	}	
}
