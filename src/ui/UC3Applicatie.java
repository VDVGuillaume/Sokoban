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

	public void UI(Scanner input) {
		int gameNumber;
		int gameboardActionNumber;
		String gameName;
		boolean gameboardCompleted;

		try {
			List<String> names = controller.getGamesList();
			System.out.println(controller.translate("GiveGameName"));
			for (int i = 0; i < names.size(); i++) {
				String gamename = (i + 1) + ". " + names.get(i);
				System.out.println(gamename);
			}

			gameNumber = input.nextInt();
			gameName = names.get(gameNumber - 1);
			controller.chooseGame(gameName);

			while (true) {
				int[] gameInfo = controller.getSelectedGameInfo();
				int totalAmountGameBoards = gameInfo[0];
				int completedAmountGameBoards = gameInfo[1];

				System.out.println(completedAmountGameBoards + " "
						+ controller.translate("NumberGameboardsCompletedOutofTotal") + " " + totalAmountGameBoards);
				
				if (totalAmountGameBoards == completedAmountGameBoards) {
					break;
				}

				System.out.println(controller.translate("GiveGameboardAction"));
				String option1 = "1." + controller.translate("CompleteNextGameboard");
				System.out.println(option1);
				String option2 = "2." + controller.translate("QuitGame");
				System.out.println(option2);
				gameboardActionNumber = input.nextInt();

				if (gameboardActionNumber == 1) {
					controller.completeGameBoard();
				} else if (gameboardActionNumber == 2) {
					controller.leaveGame();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			UI(input);
		} finally {
		}

	}
}
