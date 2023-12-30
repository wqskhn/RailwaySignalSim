package service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author waqas
 */
public class CsvReadWrite {

    public static ArrayList<Object> lines;

    public static void readFile(File selectedFile) {

        lines = new ArrayList<>();
        // double[] valueList;
        String line;
        try (Scanner sc = new Scanner(new FileReader(selectedFile))) {

            // sc.useDelimiter("[^0-9]+");
            while (sc.hasNext()) {
                line = sc.next().trim();

                lines.add(line);
            }
            sc.close();
            System.out.println("Lines structure " + lines);

        } catch (IOException ex) {
            ex.printStackTrace(); // change to alert!!!!!
        }
        // return lines;
    }

}
