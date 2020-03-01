package ui;

import java.util.Scanner;

import domein.User;

public class Menu {
	
	public static int menu(User user) {
		int selection;
	    Scanner input = new Scanner(System.in);
	    int menuItem = 0;
	   
	    System.out.printf(++menuItem+". Speel spel%n");	
	    if (user.getAdmin()) {
	    System.out.printf(++menuItem+". Maak nieuw spel%n");
	    System.out.printf(++menuItem+". Wijzig een spel%n");
	    }
	    System.out.printf(++menuItem+". Afsluiten%n");
	    
	    selection = input.nextInt();
	    return selection; 
	}
}
