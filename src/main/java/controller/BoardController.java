package controller;

import model.*;
import model.gizmos.Absorber;
import model.gizmos.Flipper;
import model.gizmos.Square;
import view.GizmoBallView;
import view.LoadBoard;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;

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
