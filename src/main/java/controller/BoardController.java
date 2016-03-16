package controller;

import model.Board;
import view.GizmoBallView;
import view.LoadBoard;

/**
 * Created by bairdjb on 11/02/2016.
 */
public class BoardController {
	private GizmoBallView view;

	public BoardController() {

		LoadBoard l = new LoadBoard();
        Board board = l.loadFile();
		if (board == null) {
			board = new Board();
			System.out.println("File reader closed");
		}
		view = new GizmoBallView(board);
		board.addObserver(view);
		board.tick();
	}
}
