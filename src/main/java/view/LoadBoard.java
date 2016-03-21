package view;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Board;
import model.GizmoParser;
/**
 * Gizmoball - LoadBoard
 *
 * Description: This class loads the board from a file.
 *
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */

public class LoadBoard {

	/**
	 * load the file
	 * @return board
     */
	public Board loadFile() {
		File filen = getFileToOpen();
		return openGizmoFromFile(filen);
	}

	/**
	 * Returns the file to be opened
	 * @return file to be opened
     */
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

	/**
	 * Opens gizmo from the file
	 * @param filen
	 * @return board
     */
	private static Board openGizmoFromFile(File filen) {
		Board board = null;
		try {
			GizmoParser g = new GizmoParser(filen);
			board = g.getGizmosFromFile();

		} catch (IOException | model.CorruptSaveFileException | NullPointerException e) {
			//
			System.out.println("IOException");
		}

		return board;
	}
}