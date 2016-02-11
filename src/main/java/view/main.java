package view;

import model.Board;
import model.Gizmos.Absorber;

/**
 * Created by Umar on 10/02/2016.
 */
public class main {
	public static void main(String[] args) {
		Board b = new Board(0, 0, 20, 20);
		GizmoBallView bg = new GizmoBallView(b);
	}
}
