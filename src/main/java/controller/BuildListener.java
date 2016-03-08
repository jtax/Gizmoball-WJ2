package controller;

import model.IBoardManager;
import view.GizmoBallView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Umar on 07/03/2016.
 */
public class BuildListener implements ActionListener {

    private IBoardManager boardManager;
    private GizmoBallView gbv;

    public BuildListener(IBoardManager bm, GizmoBallView gbv) {
        System.out.println("i work!");
        this.boardManager = bm;
        this.gbv = gbv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Run Mode":
                System.out.println("i work here, Run mode");
                gbv.switchMode();
                break;
            }


    }
}

