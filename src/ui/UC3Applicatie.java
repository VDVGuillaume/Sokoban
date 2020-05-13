package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
		UiGameBoardConsole gameBoardConsole = new UiGameBoardConsole();

		try {
			List<String> names = controller.getGamesList();
			System.out.println(controller.translate("GiveGameName"));
			for (int i = 0; i < names.size(); i++) {
				String gamename = (i + 1) + ". " + names.get(i);
				System.out.println(gamename);
			}

			gameNumber = -1;
			boolean flag = true;
			do {
				try {
					gameNumber = input.nextInt();
					System.out.println("size" + names.size());
					if (gameNumber < 1 || gameNumber >= names.size()+1) {
						throw new IllegalArgumentException(
								controller.translate("ChoiceBetween").replace("$param1", Integer.toString(1))
										.replace("$param2", Integer.toString(names.size())));
					}

					gameName = names.get(gameNumber - 1);
					controller.chooseGame(gameName);
					flag = false;
				} catch (InputMismatchException ex) {
					System.out.println("Please enter an integer.");
					input.nextLine();

				} catch (IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
				}
			} while (flag);

			while (true) {
				String[] gameInfo = controller.getSelectedGameInfo();
				String totalAmountGameBoards = gameInfo[0];
				String completedAmountGameBoards = gameInfo[1];

				System.out.println(completedAmountGameBoards + " "
						+ controller.translate("NumberGameboardsCompletedOutofTotal") + " " + totalAmountGameBoards);

				if (controller.isGameCompleted()) {
					break;
				}

				System.out.println(controller.translate("GiveGameboardAction"));
				String option1 = "1." + controller.translate("PlayNextGameboard");
				System.out.println(option1);
				String option2 = "2." + controller.translate("QuitGame");
				System.out.println(option2);
				gameboardActionNumber = input.nextInt();

				if (gameboardActionNumber == 1) {
					controller.playNextGameBoard();
					gameBoardConsole.drawConsole(controller.getSelectedGameBoardState());
				} else if (gameboardActionNumber == 2) {
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
