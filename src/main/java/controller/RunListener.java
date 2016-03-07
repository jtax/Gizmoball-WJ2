package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.BoardManager;
import model.IBoardManager;

/**
 * Created by baird on 14/02/2016.
 */
public class RunListener implements ActionListener {

	private Timer timer;
	private IBoardManager boardManager;

	public RunListener(IBoardManager bm) {
		this.boardManager = bm;
		timer = new Timer(50, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {

			boardManager.tick();
		} else {
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				break;
			case "Stop":
				timer.stop();
				break;
			case "Tick":
				boardManager.tick();
				break;
			case "Exit":
				System.exit(0);
				break;
			case "Build Mode":
				System.out.println("Build mode switch");
				break;
			}
		}
	}
}
