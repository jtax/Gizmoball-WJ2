package view;

import javax.swing.SwingUtilities;

import controller.BoardController;
import model.SaveBoardToFile;

/**
 * Created by Umar on 10/02/2016.
 */
public class Main {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new BoardController();
			}
		});

	}
}
