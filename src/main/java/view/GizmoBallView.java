package view;


import controller.KeyPressListener;
import controller.RunListener;
import model.Board;
import model.BoardManager;
import util.MagicKeyListener;
import view.BoardViews.BoardViewImpl;
import view.ButtonGroups.BuildGUI;
import view.ButtonGroups.RunGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by baird on 06/02/2016.
 */
public class GizmoBallView implements Observer {

    private final MagicKeyListener keyPressListener;
    private boolean runMode;
    private JFrame frame;
    Container contentPane;
    private RunGUI runGUI;
    private BuildGUI buildGUI;
    private JPanel bottomButtons, topButtons, boardPanel;
    private JMenuBar menu;
    private BoardView boardView;
    private BoardManager boardManager;
    private ActionListener listener;


    public GizmoBallView(BoardManager bm) {
        Board board = bm.getBoard();
        boardManager = bm;
        runMode = true;
        frame = new JFrame("Gizmo Baw");
        contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardView = new BoardViewImpl(board);
        listener = new RunListener(bm);
        keyPressListener = new MagicKeyListener(new KeyPressListener(bm.getBoard().getElements()));
        makeFrame();
    }

    public void makeFrame(){
        if(runMode){
            makeRunGUI();

            boardPanel = boardView.getPanel();


            // Listen for key events
            frame.addKeyListener(keyPressListener);
        }
        else{
            makeBuildGUI();
            contentPane.add(topButtons, BorderLayout.NORTH);
        }
        contentPane.add(boardPanel, BorderLayout.CENTER);
        contentPane.add(bottomButtons, BorderLayout.SOUTH);
        frame.setJMenuBar(menu);
        frame.setLocation(100,100);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.pack();

        //frame.setResizable(false);
    }

    private void makeRunGUI(){
        runGUI = new RunGUI(listener);
        bottomButtons = runGUI.createButton();
        menu = runGUI.createMenu();
    }

    private void makeBuildGUI(){
        buildGUI = new BuildGUI();
        bottomButtons = buildGUI.createBottomButton();
        topButtons = buildGUI.createTopButton();
        menu = buildGUI.createMenu();
    }


    @Override
    public void update(Observable o, Object arg) {

        boardView.update(o,arg);
        frame.requestFocus();

    }

}
