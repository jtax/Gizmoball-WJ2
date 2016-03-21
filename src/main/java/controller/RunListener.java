package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.IBoard;
import view.GizmoBallView;

/**
 * Gizmoball - RunListener
 *
 * Description: This class implements Action Listener, it listens to run mode
 * actions (buttons) and deals with these appropriately
 *
 * Created by Group WJ2 on 14/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class RunListener implements ActionListener {

	private Timer timer;
	private IBoard board;
	private GizmoBallView gbv;

	/**
	 * Constructor for the RunListener
	 * @param bm
	 * @param gbv
     */
	public RunListener(IBoard bm, GizmoBallView gbv) {
		this.board = bm;
		timer = new Timer(5, this);
		this.gbv = gbv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {

			board.tick();
		} else {
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				break;
			case "Stop":
				timer.stop();
				break;
			case "Tick":
				board.tick();
				break;
			case "Quit":
				quit();
				break;
			case "Build Mode":
				gbv.switchMode();
				board.startHighlighting();
				timer.stop();
				break;
			}
		}


	}

	/**
	 * Prompts the user on quit.
	 */
	private void quit(){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to quit","Warning",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			System.exit(0);
		}

	}
}
