package ui;

import java.util.Scanner;

import domein.User;

public class Menu {
	
	public static int menu(User user) {
		

		int selection;
	    Scanner input = new Scanner(System.in);

	   
	    System.out.println("1. Speel spel");	
	    if (user.isAdmin()) {
	    System.out.println("2. Maak nieuw spel");
	    System.out.println("3. Wijzig een spel");
	    }
	    System.out.println("4. Afsluiten");

	    selection = input.nextInt();
	    return selection; 
	}
	

}
