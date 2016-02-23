package view.ButtonGroups;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by baird on 06/02/2016.
 */
public class RunGUI {


    private ActionListener listener;

    public RunGUI(ActionListener listener) {
        makeFrame();
        this.listener = listener;
    }

    private void makeFrame() {
        createButton();
        createMenu();
    }

    public JPanel createButton() {
        JButton start = new JButton("Start");
        start.addActionListener(listener);
        start.setFocusable(false);

        JButton stop = new JButton("Stop");
        stop.addActionListener(listener);
        stop.setFocusable(false);

        JButton tick = new JButton("Tick");
        tick.addActionListener(listener);
        tick.setFocusable(false);

        JButton exit = new JButton("Exit");
        exit.addActionListener(listener);
        exit.setFocusable(false);

        JPanel bottomButtons = new JPanel(new GridLayout(1, 4));

        bottomButtons.add(start);
        bottomButtons.add(stop);
        bottomButtons.add(tick);
        bottomButtons.add(exit);
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
}
