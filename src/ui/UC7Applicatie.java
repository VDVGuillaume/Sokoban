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
