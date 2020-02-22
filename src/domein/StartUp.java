package domein;

import ui.UC1Applicatie;

public class StartUp {
	
	
	
	public static void main(String[] args) {
		
		DomainController domeincontroller = new DomainController();
		UC1Applicatie app = new UC1Applicatie();
		app.UI();
	}

}
