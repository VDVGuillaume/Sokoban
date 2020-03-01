package domein;

import ui.UC1Applicatie;

public class StartUp {
	
	/** UC1 StartUp class containing main method; creation of DomainController domeincontroller & UC1Applicatie app*/
	
	public static void main(String[] args) {
		
		DomainController domeincontroller = new DomainController();
		UC1Applicatie app = new UC1Applicatie(domeincontroller);
		app.UI();
	}

}
