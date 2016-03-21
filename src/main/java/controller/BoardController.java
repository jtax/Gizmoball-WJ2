package controller;

import model.Board;
import view.GizmoBallView;

/**
 * Gizmoball - BoardController
 *
 * Description: Sets up to board on initial start up.
 *
 * Created by Group WJ2 on 11/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class BoardController {

	public BoardController() {

        Board board = new Board();

		GizmoBallView view = new GizmoBallView(board);
		board.addObserver(view);
		board.tick();
	}
}
