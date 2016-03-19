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

        double x = scaleX(e.getX());
        double y = scaleY(e.getY());
        board.setMouseClick(new Vect(x, y));
        bView.getPanel().repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = scaleX(e.getX());
        double y = scaleY(e.getY());
        board.setMousePress(new Vect(x, y));
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

    private double scaleX(double val) {
        return val / bView.getHorizontalScalingFactor();
    }

    private double scaleY(double val) {
        return val / bView.getVerticalScalingFactor();
    }
}
