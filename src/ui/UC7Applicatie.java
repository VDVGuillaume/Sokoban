package ui;

import java.util.InputMismatchException;
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
		int gameNumber = -1;
		int gameBoardIdChoice = -1;
		int gameBoardOption = -1;
		List<String> names = controller.getGamesListCreatedByUser();
		System.out.println(controller.translate("GiveGameName"));
		for (int i = 0; i < names.size(); i++) {
			String gamename = (i + 1) + ". " + names.get(i);
			System.out.println(gamename);
		}

		try {
			gameNumber = input.nextInt();
			if (gameNumber < 1 || gameNumber > names.size()) {
				throw new IllegalArgumentException(controller.translate("ChoiceBetween")
						.replace("$param1", Integer.toString(1)).replace("$param2", Integer.toString(names.size())));
			}
			gameName = names.get(gameNumber - 1);
			controller.chooseGame(gameName);
			List<Integer> gameBoardIds = controller.getGameBoardIdsFromGame();
			System.out.println(controller.translate("GiveGameBoardName"));
			for (int i = 0; i < gameBoardIds.size(); i++) {
				String gameBoardId = (i + 1) + ". " + gameBoardIds.get(i);
				System.out.println(gameBoardId);
			}

			chooseGameboardId(gameBoardIdChoice, gameBoardOption, gameBoardIds, input);

		} catch (InputMismatchException ex) {
			System.out.println(controller.translate("GiveIntegerNumber"));
			input.nextLine();
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			input.nextLine();
		}
	}

	public void chooseGameboardId(int gameBoardIdChoice, int gameBoardOption, List<Integer> gameBoardIds,
			Scanner input) {
		try {
			gameBoardIdChoice = input.nextInt();
			if (gameBoardIdChoice < 1 || gameBoardIdChoice > gameBoardIds.size()) {
				throw new IllegalArgumentException(
						controller.translate("ChoiceBetween").replace("$param1", Integer.toString(1)).replace("$param2",
								Integer.toString(gameBoardIds.size())));
			}
			controller.chooseGameBoardFromGame(gameBoardIds.get(gameBoardIdChoice - 1));
			System.out.println(controller.translate("OptionsForGameboard"));
			String option1 = "1." + controller.translate("EditGameboard");
			System.out.println(option1);
			String option2 = "2." + controller.translate("SaveGame");
			System.out.println(option2);
			String option3 = "3." + controller.translate("DeleteGameboard");
			System.out.println(option3);
			chooseGameBoardOption(gameBoardIdChoice, gameBoardOption, input);
		} catch (InputMismatchException ex) {
			System.out.println(controller.translate("GiveIntegerNumber"));
			input.nextLine();
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			input.nextLine();
		}
	}

	public void chooseGameBoardOption(int gameBoardIdChoice, int gameBoardOption, Scanner input) {
		int[] selector = { 5, 5, 0 };

		int selectorXCoordinate = selector[0];
		int selectorYCoordinate = selector[1];
		String[] action = {"clear","wall","pawn","goal","box"};
		String userInput = new String();
		int actionIndex = 0;
		UiGameBoardConsole gameBoardConsole = new UiGameBoardConsole();

		try {
			gameBoardOption = input.nextInt();
			if (gameBoardOption < 1 || gameBoardOption > 3) {
				throw new IllegalArgumentException(controller.translate("ChoiceBetween")
						.replace("$param1", Integer.toString(1)).replace("$param2", Integer.toString(3)));
			}

			switch (gameBoardOption) {
			case 1:
				String gameboardInfo[][] = controller.getSelectedGameBoardState();
				String[][] gameboardInfoWithSelector = gameboardInfo;
				gameboardInfoWithSelector[selectorYCoordinate][selectorXCoordinate] = "Selector";
				gameBoardConsole.drawConsole(gameboardInfoWithSelector);

				do {

					System.out.println(controller.translate("GameBoardCreationPossibleMoves"));
					char c = input.next().charAt(0);

					switch (c) {
					case 'Q':
					case 'q':
						selectorYCoordinate--;

						selector[2] = 0;
						break;
					case 'Z':
					case 'z':
						selectorXCoordinate--;

						selector[2] = 0;
						break;
					case 'S':
					case 's':
						selectorXCoordinate++;
						selector[2] = 0;
						break;
					case 'D':
					case 'd':
						selectorYCoordinate++;

						selector[2] = 0;
						break;
					case 'E':
					case 'e':
						String piece = new String();
						piece = action[actionIndex];
						controller.setPositionAction(selectorXCoordinate, selectorYCoordinate, piece);
						actionIndex++;
						if (actionIndex > 4) {
							actionIndex = 0;
						}
						selector[2] = 1;

						break;
					case 'A':
					case 'a':
						userInput = "save";
						controller.changeGameboard();
						break;
					case 'T':
					case 't':
						return;
					default:
						break;
					}

					if (selectorYCoordinate > 9 || selectorYCoordinate < 0 || selectorXCoordinate > 9
							|| selectorXCoordinate < 0) {
						selectorYCoordinate = selector[1];
						selectorXCoordinate = selector[0];
						continue;
					} else {
						selector[0] = selectorXCoordinate;
						selector[1] = selectorYCoordinate;
					}

					gameboardInfo = controller.getSelectedGameBoardState();

					if (selector[2] == 0) {
						gameboardInfoWithSelector = gameboardInfo;
						gameboardInfoWithSelector[selectorXCoordinate][selectorYCoordinate] = "Selector";
						gameBoardConsole.drawConsole(gameboardInfoWithSelector);
					} else {

						gameBoardConsole.drawConsole(gameboardInfo);
					}

				} while (!userInput.equals("save"));
				break;
			case 2:
				controller.changeGameboard();
				controller.saveGameBoard();
				break;
			case 3:
				controller.deleteSelectedGameBoard();
				break;
			}
		} catch (InputMismatchException ex) {
			System.out.println(controller.translate("GiveIntegerNumber"));
			input.nextLine();
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			input.nextLine();
		}
	}
}
