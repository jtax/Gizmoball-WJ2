package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Gizmoball - Save
 * Created by Group WJ2 on 18/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Save {

    public File getSaveFile() {
        File file;
        JFileChooser saveFile = new JFileChooser();
        saveFile.setCurrentDirectory(new File("/home/me/Documents"));
        saveFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        saveFile.setAcceptAllFileFilterUsed(false);
        saveFile.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        int retrieval = saveFile.showSaveDialog(null);
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            file = saveFile.getSelectedFile();
        } else {
            System.out.println("save file not selected");
            return null;
        }
        return file;
    }
}
