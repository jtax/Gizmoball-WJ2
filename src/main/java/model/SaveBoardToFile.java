package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import view.Save;

/**
 * Gizmoball - SaveBoardToFile
 *
 * Description: This will save the board to a file.
 *
 * Created by Group WJ2 on 01/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class SaveBoardToFile {

	/**
	 * This will save the board to a file.
	 * @param board
	 * @return true if save was successful
     */
	public boolean saveBoard(Board board) {

		if (board != null) {

			Collection<IElement> elements = board.getElements();
			Save save = new Save();
			File file = save.getSaveFile();
			if (file == null) {
				return false;
			}

			try {
				FileWriter fileWriter = new FileWriter(file + ".txt");

				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				for (IElement element : elements) {

					if (element.getSaveInfo().equals("Wall")) {
						continue;
					}


					bufferedWriter.write(element.getSaveInfo());
					bufferedWriter.newLine();
					StringTokenizer st = new StringTokenizer(element.getSaveInfo(), " ");
						String name = st.nextToken();
						switch (element.getRotation()) {
							case 0:
								break;
							case 1:
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								break;
							case 2:
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								break;
							case 3:
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								bufferedWriter.write("Rotate" + " " + name);
								bufferedWriter.newLine();
								break;

					}

					if(!element.getConnections().isEmpty()){
						List<String> connections = element.getConnections();
						for(String connection : connections) {
							bufferedWriter.write(connection);
							bufferedWriter.newLine();
						}
					}

					if(!element.returnKeyConnects().isEmpty()){
						List<String> connections = element.returnKeyConnects();
						for (String connection : connections) {
							bufferedWriter.write(connection);
							bufferedWriter.newLine();
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
				return false;
			}
		} else {
			// board doesnt exist
			return false;
		}

	}


}
