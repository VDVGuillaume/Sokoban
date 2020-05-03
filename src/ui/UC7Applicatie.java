package ui;

import java.util.List;
import java.util.Scanner;
import domein.DomainController;

public class UC7Applicatie {
		private DomainController controller;
		
		public UC7Applicatie(DomainController controller) {
			this.controller = controller;
			

		}
		
		public void UI(Scanner input) {
			String gameName;
			int gameNumber;
			int gameBoardIdChoice;
			int gameBoardOption;
			UiGameBoardConsole gameBoardConsole = new UiGameBoardConsole();

			try {
				List<String> names = controller.getGamesListCreatedByUser();
				System.out.println(controller.translate("GiveGameName"));
				for (int i = 0; i < names.size(); i++) {
					String gamename = (i + 1) + ". " + names.get(i);
					System.out.println(gamename);
				}

				gameNumber = input.nextInt();
				gameName = names.get(gameNumber - 1);
				controller.chooseGame(gameName);
				try {
					List<Integer> gameBoardIds= controller.getGameBoardIdsFromGame();
					System.out.println(controller.translate("GiveGameBoardName"));
					for (int i = 0; i < gameBoardIds.size(); i++) {
						String gameBoardId = (i + 1) + ". " + gameBoardIds.get(i);
						System.out.println(gameBoardId);
					}
					gameBoardIdChoice = input.nextInt();
					controller.chooseGameBoardFromGame(gameBoardIds.get(gameBoardIdChoice-1));
					System.out.println(controller.translate("OptionsForGameboard"));
					String option1 = "1." + controller.translate("EditGameboard");
					System.out.println(option1);
					String option2 = "2." + controller.translate("SaveGame");
					System.out.println(option2);
					String option3 = "3." + controller.translate("DeleteGameboard");
					System.out.println(option3);
					
					gameBoardOption=input.nextInt();
					
					switch(gameBoardOption) {
					   case 1 :
						   //UC8 edit gameboard
						   break;
					   case 2 :
						   controller.updateGame();
						   break;
					   case 3 :
						   controller.deleteSelectedGameBoard();
						   break;
					}
				}catch (Exception e){
					System.out.println(e.getMessage());
					input.nextLine();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				input.nextLine();
			} 
		}
}
