package model;

/**
 * Created by Umar on 02/03/2016.
 */
public interface IBoardManager {
	Board getBoard();

	void setBoard(Board board);

	void tick();
}
