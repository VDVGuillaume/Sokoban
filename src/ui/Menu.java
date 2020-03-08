package ui;

import java.util.Scanner;

import domein.User;

public class Menu {
	
	public static int menu(User user) {
		int selection;
	    Scanner input = new Scanner(System.in);
	    int menuItem = 0;
	   
	    System.out.println(++menuItem+". Speel spel");	
	    if (user.getAdmin()) {
	    System.out.println(++menuItem+". Maak nieuw spel");
	    System.out.println(++menuItem+". Wijzig een spel");
	    }
	    System.out.println(++menuItem+". Afsluiten");
	    
	    selection = input.nextInt();
	    return selection; 
	}
}
