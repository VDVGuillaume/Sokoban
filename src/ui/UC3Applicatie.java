package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import domein.Game;
import domein.GameBoard;

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
	Game game;
	boolean gameboardCompleted;
	GameBoard gameBoard;
	
		try {
		List <String> names = new ArrayList<String>();
		names= controller.getGamesList();
		System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GiveGameName"));
		for(int i=0; i<names.size();i++) {
			String gamename =(i+1) + ". " + names.get(i);
			System.out.println(gamename);	
		}
		gameNumber=input.nextInt();
		game=controller.chooseGame(names.get(gameNumber-1));
		
		System.out.println(ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("GiveGameboardAction"));
		String option1 ="1." + ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("CompleteNextGameboard");
		System.out.println(option1);
		String option2 ="2." + ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("QuitGame");
		System.out.println(option2);
		gameboardActionNumber=input.nextInt();
		
		switch (gameboardActionNumber) {
		case 1:
		gameBoard = game.getNextGameBoard();
		game.completeGameBoard(gameBoard);
		break;
		case 2:
		System.exit(0);
		break;
		}
		
		System.out.println(game.getAmountBoardsCompleted() + " " + ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault()).getString("NumberGameboardsCompletedOutofTotal") + " " + game.getAmountBoardsTotal());
		}catch (Exception e) {
			System.out.println(e.getMessage());
			UI();
		}
		finally {
			input.close();
		}
		
		
	}
}
