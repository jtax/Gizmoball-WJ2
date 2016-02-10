package view.ButtonGroups;

import view.ButtonGroup;

import javax.swing.*;
import java.awt.*;

/**
 * Created by baird on 06/02/2016.
 */
public class RunGUI implements ButtonGroup {
    private JFrame frame;
    Container contentPane;

    public RunGUI(){
        frame = new JFrame("Run Mode");
        contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(3);
        makeFrame();
    }

    private void makeFrame() {
        createButton();
        createMenu();
        frame.setSize(500,600);
        frame.setLocation(550, 300);
        frame.setVisible(true);
    }

    @Override
    public void createButton() {
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton build = new JButton("Build Mode");

        JPanel bottomButtons = new JPanel(new GridLayout(1,3));

        bottomButtons.add(start);
        bottomButtons.add(stop);
        bottomButtons.add(build);
        contentPane.add(bottomButtons, BorderLayout.SOUTH);
    }

    @Override
    public void createMenu() {
        JMenuBar menus = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem tick = new JMenuItem("Tick");
        file.add(tick);

        JMenuItem quit = new JMenuItem("Quit");
        file.add(quit);

        menus.add(file);

        frame.setJMenuBar(menus);
    }
}
