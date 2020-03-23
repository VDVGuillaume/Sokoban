package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import domein.Game;
import domein.GameBoard;
import util.Language;
import domein.DomainController;

public class UC3Applicatie {
	private DomainController controller;
	
	public UC3Applicatie(DomainController controller) {
		this.controller = controller;
	}
	
	public void UI() {	
	Scanner input = new Scanner(System.in);
	int gameNumber;
	int gameboardActionNumber;
	String gameName;
	Game game;
	boolean gameboardCompleted;
	GameBoard gameBoard;
	
		try {
		List <String> names = new ArrayList<String>();
		names= controller.getGamesList();
		System.out.println(controller.translate("GiveGameName"));
		for(int i=0; i<names.size();i++) {
			String gamename =(i+1) + ". " + names.get(i);
			System.out.println(gamename);	
		}
		gameNumber=input.nextInt();
		game=controller.chooseGame(names.get(gameNumber-1));
		
		while(game.allBoardsGameCompleted()==false) {
		System.out.println(controller.translate("GiveGameboardAction"));
		String option1 ="1." + controller.translate("CompleteNextGameboard");
		System.out.println(option1);
		String option2 ="2." + controller.translate("QuitGame");
		System.out.println(option2);
		gameboardActionNumber=input.nextInt();
		gameName = game.getName();
		controller.chooseGameBoardAction(gameboardActionNumber, gameName);
		
		System.out.println(game.getAmountBoardsCompleted() + " " + controller.translate("NumberGameboardsCompletedOutofTotal") + " " + game.getAmountBoardsTotal());
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			UI();
		}
		finally {
			input.close();
		}
		
		
	}
}
