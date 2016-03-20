package controller;

import model.Board;
import view.GizmoBallView;
import view.LoadBoard;

/**
 * Gizmoball - BoardController
 * Created by Group WJ2 on 11/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class BoardController {

	public BoardController() {

		LoadBoard l = new LoadBoard();
        Board board = l.loadFile();
		if (board == null) {
			board = new Board();
			System.out.println("File reader closed");
		}
		GizmoBallView view = new GizmoBallView(board);
		board.addObserver(view);
		board.tick();
	}
}
