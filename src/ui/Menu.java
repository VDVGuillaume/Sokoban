package ui;

import java.util.ResourceBundle;
import java.util.Scanner;

import domein.User;
import util.Language;

public class Menu {
	
	public static int menu(String admin) {
		int selection;
	    Scanner input = new Scanner(System.in);
	    int menuItem = 0;
	   
	    System.out.println(++menuItem+ Language.translate("Menu_PlayGame"));	
	    if (admin == "True") {
	    System.out.println(++menuItem+ Language.translate("Menu_CreateNewGame"));
	    System.out.println(++menuItem+ Language.translate("Menu_EditGame"));
	    }
	    System.out.println(++menuItem+ Language.translate("Menu_Quit"));
	    
	    selection = input.nextInt();
	    return selection; 
	}
}
