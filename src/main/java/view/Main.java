package view;

import controller.BoardController;

import javax.swing.*;

/**
 * Created by Umar on 10/02/2016.
 */
public class Main {
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new BoardController();
			}
		});

	}
}
