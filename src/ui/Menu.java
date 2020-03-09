package ui;

import java.util.ResourceBundle;
import java.util.Scanner;

import domein.User;
import util.Language;

public class Menu {
	
	public static int menu(User user) {
		int selection;
	    Scanner input = new Scanner(System.in);
	    int menuItem = 0;
	   
	    System.out.println(++menuItem+ ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("Menu_PlayGame"));	
	    if (user.getAdmin()) {
	    System.out.println(++menuItem+ ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("Menu_CreateNewGame"));
	    System.out.println(++menuItem+ ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("Menu_EditGame"));
	    }
	    System.out.println(++menuItem+ ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("Menu_Quit"));
	    
	    selection = input.nextInt();
	    return selection; 
	}
}
