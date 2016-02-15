package view;


import model.Board;
import view.BoardViews.BoardViewImpl;
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
    private BoardView boardView;

    public GizmoBallView(Board board) {
        runMode = false;
        frame = new JFrame("Gizmo Baw");
        contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardView = new BoardViewImpl(board);
        makeFrame();
    }

    public void makeFrame(){
        try {
            // Use native theme
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        boardPanel = boardView.getPanel();

        if(runMode){
            makeRunGUI();
        }
        else{
            makeBuildGUI();
            contentPane.add(topButtons, BorderLayout.NORTH);
        }
        addFrameFeatures();
    }

    private void addFrameFeatures() {
        contentPane.add(boardPanel, BorderLayout.CENTER);
        contentPane.add(bottomButtons, BorderLayout.SOUTH);
        frame.setJMenuBar(menu);
        frame.pack();
        frame.setLocation(100,100);
        frame.setVisible(true);
        frame.setResizable(false);
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
        boardView.update(o,arg);
    }

}
