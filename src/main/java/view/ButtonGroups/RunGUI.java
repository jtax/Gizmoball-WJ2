package view.ButtonGroups;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by baird on 06/02/2016.
 */
public class RunGUI implements ActionListener {


    public RunGUI(){
        makeFrame();
    }

    private void makeFrame() {
        createButton();
        createMenu();
    }

    public JPanel createButton() {
        JButton start = new JButton("Start");
        start.addActionListener(this);
        JButton stop = new JButton("Stop");
        JButton build = new JButton("Build Mode");

        JPanel bottomButtons = new JPanel(new GridLayout(1,3));

        bottomButtons.add(start);
        bottomButtons.add(stop);
        bottomButtons.add(build);
        return bottomButtons;
    }

    public JMenuBar createMenu() {
        JMenuBar menus = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem tick = new JMenuItem("Tick");
        file.add(tick);

        JMenuItem quit = new JMenuItem("Quit");
        file.add(quit);

        menus.add(file);

        return menus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.printf("I worked");
    }
}
