package view;


import view.BoardViews.RunBoard;
import view.ButtonGroups.BuildGUI;
import view.ButtonGroups.RunGUI;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by baird on 06/02/2016.
 */
public class GizmoBallView implements Observer {

    private boolean runMode;
    private JFrame frame;
    Container contentPane;
    private RunGUI runGUI;
    private BuildGUI buildGUI;
    private JPanel bottomButtons, topButtons, boardPanel;
    private JMenuBar menu;
    private JPanel runBoard;


    public GizmoBallView(    ) {
        runMode = true;
        frame = new JFrame("Gizmo Baw");
        contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(3);
        runBoard = new RunBoard();
        runBoard.setSize(500,500);
        makeFrame();
    }

    public void makeFrame(){
        if(runMode == true){
            makeRunGUI();
            boardPanel = runBoard;
        }
        else{
            makeBuildGUI();
            contentPane.add(topButtons, BorderLayout.NORTH);
        }
        contentPane.add(boardPanel, BorderLayout.CENTER);
        contentPane.add(bottomButtons, BorderLayout.SOUTH);
        frame.setJMenuBar(menu);
        frame.setSize(475,550);
        frame.setLocation(550, 300);
        frame.setVisible(true);
    }

    private void makeRunGUI(){
        runGUI = new RunGUI();
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
        //repaint();
    }
}
