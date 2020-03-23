package ui;

import java.util.Scanner;
import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomainController;
import domein.UserRepository;
import exceptions.PasswordException;
import util.Language;

public class UC2Applicatie {
	private DomainController controller;

	public UC2Applicatie(DomainController controller) {
		this.controller = controller;
	}

	public String[] UI(Scanner input) {
		String username;
		String password;
		String firstName;
		String name;
		String[] info = new String[2];

		try {

			System.out.println(controller.translate("GiveName"));
			name = input.nextLine();
			System.out.println(controller.translate("GiveFirstName"));
			firstName = input.nextLine();
			System.out.println(controller.translate("GiveUsername"));
			username = input.nextLine();
			System.out.println(controller.translate("GivePassword"));
			password = input.nextLine();

			controller.register(name, firstName, username, password);

			info = controller.getInfoUser();
			System.out.println("gebruikersnaam = " + info[0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			UI(input);
		} finally {
		}

		return info;
	}
}