package model;

/**
 * Created by baird on 06/02/2016.
 */
public class BoardManager {
    Board board;

    public BoardManager(){
        board = new Board(0,0,20,20);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}