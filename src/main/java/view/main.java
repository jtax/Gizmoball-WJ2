package view;

import controller.boardController;

import javax.swing.*;

/**
 * Created by Umar on 10/02/2016.
 */
public class main {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new boardController();
			}
		});

	}
}
