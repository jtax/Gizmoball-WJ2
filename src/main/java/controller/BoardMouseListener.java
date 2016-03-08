package controller;

import view.BoardViewImpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by baird on 08/03/2016.
 */
public class BoardMouseListener implements MouseListener {
    private BoardViewImpl bView;

    public BoardMouseListener(BoardViewImpl bView) {
        this.bView = bView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click X: " + scaleX(e.getX()) + " Y: " + scaleY(e.getY()));
        bView.highLight(scaleX(e.getX()), scaleY(e.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Release X: " + scaleX(e.getX()) + " Y: " + scaleY(e.getY()));
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
