package view;

import controller.BoardController;

import javax.swing.*;

/**
 * Gizmoball - Main
 * Created by Group WJ2 on 10/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Main {
	public static void main(String[] args) {

		String username = System.getProperty("user.name");
		if (username == null || !(username.equals("chris") || username.equals("wrb13148"))) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				// Never mind. We'll manage fine without it.
			}
		}

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new BoardController();
			}
		});


	}
}
