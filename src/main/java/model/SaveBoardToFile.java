package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ElChupacobra on 01/03/2016.
 */
public class SaveBoardToFile {

    public boolean saveBoard(Board board) {

        if (board != null) {

            List<IElement> elements = board.getElements();

            String fileName = "test" + ".txt";

            try {
                FileWriter fileWriter = new FileWriter(fileName);

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                System.out.println("here");
                for (IElement element : elements) {
                    if(!element.getSaveInfo().equals("Wall")) {
                        bufferedWriter.write(element.getSaveInfo());
                        bufferedWriter.newLine();
                    }
                }
                for(int i = 0; i < board.getBalls().size(); i++) {
                    bufferedWriter.write(board.getBalls().get(i).getSaveInfo());
                }
                bufferedWriter.newLine();
                bufferedWriter.write("Gravity"+" "+ String.format("%.0f",board.getGravityConst()));
                bufferedWriter.newLine();
                bufferedWriter.write("Friction" + " " + String.format("%.3f",board.getFrictionConst()[0]) +" " + String.format("%.3f",board.getFrictionConst()[1]));

                // Always close files.
                bufferedWriter.close();
                return true;
            } catch (IOException ex) {
                System.out.println("Error writing to file '" + fileName + "'");
                return false;
            }
        }
            else{
                //board doesnt exist
                return false;
            }

        }
    }

