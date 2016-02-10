package view.ButtonGroups;

import view.ButtonGroup;
import javax.swing.*;
import java.awt.*;

/**
 * Created by baird on 06/02/2016.
 */
public class BuildGUI  implements ButtonGroup {
    private JFrame frame;
    private JButton add, select, remove, switchToRun;
    private JComboBox<String> shape;
    //private JComboBox<Integer> size;
   // private JTextArea output;
    private JButton absorber;
    private JButton ball;
    private JButton flipper;
    private Container contentPane;

    public BuildGUI(){
        frame = new JFrame("Build Mode");
        contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(3);
        makeFrame();
    }

    public void makeFrame() {
        createButton();
        createMenu();
        frame.setSize(500,800);
        frame.setLocation(550, 300);
        frame.setVisible(true);
    }

    @Override
    public void createButton() {
        add = new JButton("Add");
        select = new JButton("Select");
        remove = new JButton("Remove");

        JPanel topButtons = new JPanel(new GridLayout(1,3));
        topButtons.add(add);
        topButtons.add(select);
        topButtons.add(remove);

        switchToRun = new JButton("Run Mode");
        shape = new JComboBox<String>();
        shape.addItem("Pick a gizmo");
        shape.addItem("Square");
        shape.addItem("Circle");
        shape.addItem("Triangle");
        shape.addItem("Left Flipper");
        shape.addItem("Right Flipper");
        shape.addItem("Absorber");
        JButton rotate = new JButton("Rotate");
        JButton move = new JButton("Move");
        JButton friction = new JButton("Friction");
        JButton gravity = new JButton("Gravity");
        JButton keyConn = new JButton("Key Connection");
        JButton connGizmo = new JButton("Gizmo Connection");
        ball = new JButton("Ball");

        JPanel bottomButtons = new JPanel(new GridLayout(3,5));
        bottomButtons.add(switchToRun);
        bottomButtons.add(shape);
        bottomButtons.add(ball);
        bottomButtons.add(rotate);
        bottomButtons.add(move);
        bottomButtons.add(friction);
        bottomButtons.add(gravity);
        bottomButtons.add(keyConn);
        bottomButtons.add(connGizmo);

        contentPane.add(topButtons, BorderLayout.NORTH);
        //contentPane.add(p2, BorderLayout.CENTER);
        contentPane.add(bottomButtons, BorderLayout.SOUTH);

    }

    @Override
    public void createMenu() {
        JMenuBar menus = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem loadModel = new JMenuItem("Load Board");
        file.add(loadModel);

        JMenuItem saveModel = new JMenuItem("Save Board");
        file.add(saveModel);

        JMenuItem undo = new JMenuItem("Undo build");
        file.add(undo);

        JMenuItem redo = new JMenuItem("Redo build");
        file.add(redo);

        JMenuItem quit = new JMenuItem("Quit");
        file.add(quit);

        menus.add(file);

        frame.setJMenuBar(menus);
    }
}
