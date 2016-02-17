package view;

import model.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;


public class LoadBoard {

    public static void main(String[] args){
        File filen = getFileToOpen();
        if(filen != null){
            boolean result = openGizmoFromFile(filen);
            if(result == false){
                System.out.println("File not loaded successfully");
            }else{
                System.out.println("File loaded successfully");
            }
        }
    }

    public static File getFileToOpen() {

        JFileChooser openFile = new JFileChooser();
        openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        openFile.setAcceptAllFileFilterUsed(false);
        openFile.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));

        int returnVal = openFile.showOpenDialog(openFile);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return openFile.getSelectedFile();
        } else {
            return null;
        }

    }

    public static boolean openGizmoFromFile(File filen) {



        try{
            GizmoParser g = new GizmoParser(filen);
            g.getGizmosFromFile();


        }catch(IOException e){
            //
            System.out.println("IOException");
            return false;
        } catch (model.BadFileException e) {
            e.printStackTrace();
        }

        return true;
    }
}