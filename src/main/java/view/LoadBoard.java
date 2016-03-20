package view;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Board;
import model.GizmoParser;

public class LoadBoard {

	public Board loadFile() {
		File filen = getFileToOpen();
		return openGizmoFromFile(filen);
	}

	private static File getFileToOpen() {

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

	private static Board openGizmoFromFile(File filen) {
		Board board = null;
		try {
			GizmoParser g = new GizmoParser(filen);
			board = g.getGizmosFromFile();

		} catch (IOException | model.BadFileException | NullPointerException e) {
			//
			System.out.println("IOException");
		}

		return board;
	}
}