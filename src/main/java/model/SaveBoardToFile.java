package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by ElChupacobra on 01/03/2016.
 */
public class SaveBoardToFile {

	public boolean saveBoard(Board board) {

		if (board != null) {

			Collection<IElement> elements = board.getElements();

			File file = getSaveFile();
			if (file == null) {
				return false;
			}

			try {
				FileWriter fileWriter = new FileWriter(file + ".txt");

				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				System.out.println("here");
				for (IElement element : elements) {

					if (element.getSaveInfo().equals("Wall")) {
						continue;
					}

					bufferedWriter.write(element.getSaveInfo());
					bufferedWriter.newLine();
					StringTokenizer st = new StringTokenizer(element.getSaveInfo(), " ");
						String type  =st.nextToken();
						String name = st.nextToken();
					if(!type.equals("RightFlipper")&& !type.equals("LeftFlipper")) {
						switch (element.getRotation()) {
							case 2:
								break;
							case 3:
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								break;
							case 0:
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								break;
							case 1:
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								break;
						}
					}
					}


				for (Ball ball : board.getBalls()) {
					bufferedWriter.write(ball.getSaveInfo());
				}
				bufferedWriter.newLine();
				bufferedWriter.write("Gravity" + " " + String.format("%.0f", board.getGravityConst()));
				bufferedWriter.newLine();
				bufferedWriter.write("Friction" + " " + String.format("%.3f", board.getFrictionConst()[0]) + " "
						+ String.format("%.3f", board.getFrictionConst()[1]));

				// Always close files.
				bufferedWriter.close();
				return true;
			} catch (IOException ex) {
				System.out.println("Error writing to file");
				return false;
			}
		} else {
			// board doesnt exist
			return false;
		}

	}

	public File getSaveFile() {
		File file;
		JFileChooser saveFile = new JFileChooser();
		saveFile.setCurrentDirectory(new File("/home/me/Documents"));
		saveFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		saveFile.setAcceptAllFileFilterUsed(false);
		saveFile.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
		int retrival = saveFile.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			file = saveFile.getSelectedFile();
		} else {
			System.out.println("save file not selected");
			return null;
		}
		return file;
	}
}
