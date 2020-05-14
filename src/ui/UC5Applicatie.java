package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomainController;

public class UC5Applicatie {

	private DomainController controller;

	public UC5Applicatie(DomainController controller) {
		this.controller = controller;

	}

	public void UI(Scanner input) {

		try {
			System.out.println(controller.translate("GiveGameName"));
			String gameName = input.nextLine();
			controller.createGame(gameName);
			int answer = 0;
			do {
				controller.createGameBoard();
				System.out.println(controller.translate("addGameBoard"));
				try {
					answer = input.nextInt();
					if (answer < 0 || answer > 1) {
						throw new IllegalArgumentException(controller.translate("ChoiceBetween")
								.replace("$param1", Integer.toString(0)).replace("$param2", Integer.toString(1)));
					}
					controller.createGameBoard();
				} catch (InputMismatchException ex) {
					System.out.println(controller.translate("GiveIntegerNumber"));
					input.nextLine();
				} catch (IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					input.nextLine();
				}	
			} while (answer == 1);
			if (answer == 0) {
				String gameInfo[] = controller.getSelectedGameInfo();
				//System.out.println(controller.translate("GameCreated"));
				System.out.printf("%s %s%n", gameInfo[2], gameInfo[0]);
				controller.saveGame();
			}
			else{
				throw new InputMismatchException();
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
			UI(input);

		}

	}
}
