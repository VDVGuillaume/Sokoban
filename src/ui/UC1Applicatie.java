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
	String 	username;
	String  password;
	String[] info = new String[2];
	Scanner input = new Scanner(System.in);
	
		try {
		
		System.out.println(controller.translate("GiveUsername"));
		username = input.nextLine();
		System.out.println(controller.translate("GivePassword"));
		password = input.nextLine();
		
		controller.login(username, password);
		info = controller.getInfoUser();
		System.out.println("gebruikersnaam = " + info[0]);
		}catch (Exception e) {
			e.printStackTrace();		
			UI();		
		}
		finally {
			
		}
		
		return info;
	}	
}
