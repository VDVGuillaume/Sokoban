package domein;

import java.util.Scanner;


public class UC1Applicatie {
	
	
	public UC1Applicatie() {
		
	DomainController controller = new DomainController();	
	Scanner input = new Scanner(System.in);
	
	String 	username;
	String  password;
	
	System.out.print("Give username: ");
	username = input.nextLine();
	System.out.print("Give password: ");
	password = input.nextLine();
	
	controller.logIn(username, password);
	
			
		
	}
	
	
}
