package controller;

import model.IBoard;
import physics.Vect;
import view.BoardViewImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by baird on 08/03/2016.
 */
public class BoardMouseListener implements MouseListener {
    private BoardViewImpl bView;
    private IBoard board;

    public BoardMouseListener(BoardViewImpl bView, IBoard board) {
        this.bView = bView;
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        board.setMouseClick(new Vect(scaleX(e.getX()), scaleY(e.getY())));
        bView.getPanel().repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        board.setMousePress(new Vect(scaleX(e.getX()), scaleY(e.getY())));
        bView.getPanel().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        board.setMouseRelease(new Vect(scaleX(e.getX()), scaleY(e.getY())));
        bView.getPanel().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private int scaleX(int val) {
        return val / bView.getHorizontalScalingFactor();
    }

    private int scaleY(int val) {
        return val / bView.getVerticalScalingFactor();
    }
}
