package service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author waqas
 */
public class GradientCalc {

    /**
     *
     * @param selectedFile
     * @return
     */
    public static Object[][] readXlsxBook(File selectedFile) {
        Workbook workbook = null;
        Sheet sheet;

        Object[][] locationData = null;
        Object[][] trainData = null;

        try {
            InputStream input = new BufferedInputStream(
                    new FileInputStream(selectedFile));//"Book1.xlsx"
            workbook = new XSSFWorkbook(input);
            // two sheets
            for (int i = 0; i < 2; i++) {
                int rownum = 0;
                int colnum = 0;
                sheet = workbook.getSheetAt(i); // while loop

                Iterator rows = sheet.rowIterator();
                Row r = (Row) rows.next();
                int rowcount = sheet.getLastRowNum();
                int colcount = sheet.getRow(0).getLastCellNum();
                locationData = new Object[rowcount][colcount];

                while (rows.hasNext()) {
                    Row row = (Row) rows.next();
                    Iterator cells = row.cellIterator();
                    colnum = 0;

                    while (cells.hasNext()) {

                        Cell cell = (Cell) cells.next();
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:

                                locationData[rownum][colnum] = cell.getNumericCellValue();
                                colnum++;
                                break;
                            case Cell.CELL_TYPE_STRING:

                                locationData[rownum][colnum] = cell.getStringCellValue();
                                colnum++;
                            case Cell.CELL_TYPE_BOOLEAN:

                                break;
                            case Cell.CELL_TYPE_BLANK: {
                               
                            }
                            default: {
                                
                            }// System.out.print("Unknown cell type"); //warning
                            break;
                        }

                    }
                    rownum++;

                }

                List<Object[]> outerList = new ArrayList<>(locationData.length);

                for (Object[] inner : locationData) {
                    if (inner != null) {
                        List<Object> list = new ArrayList<>(inner.length);

                        for (Object inner1 : inner) {
                            if (inner1 != null) {

                                list.add(inner1);
                            }
                        }
                        outerList.add(list.toArray(new Object[list.size()]));

                    }
                }

                locationData = outerList.toArray(new Object[outerList.size()][]);

                if (i == 0) {
                    trainData = locationData;
                }

            }// end of for loop

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Hashtable accelTable = averageGradient(locationData);
        Object[][] data = dataWithGradient(accelTable, trainData);

        return data;

    }

    private static Hashtable averageGradient(Object[][] locationData) {
        IniReadWrite readWrite =new IniReadWrite();
        double coefficient = readWrite.readIni();
        Hashtable gradientTable = new Hashtable();
        for (Object[] locationData1 : locationData) {
            Object key;
            double gradient = 0.0;
            double D = 0.0;
            double l = 0.0;
            key = locationData1[0]; // watch for
            for (int j = 2; j < locationData1.length; j += 2) {
                //int k=j-1;
                double d = (double) locationData1[j - 1];
                double g = (double) locationData1[j];
                l = l + d / g;
                D = d + D;
            }
            gradient = D / l;
            double a = acceleration(gradient, coefficient);
            // acceleration altered due to gradient
            gradientTable.put(key, a);
        }

        return gradientTable;
    }

   
    /**
     * Calculate the acceleration due to gradient affect formula [a=
     * 9.79*(braking coefficient+9.79/gradient]
     *
     * @param gradient
     * @param coefficient
     * @return
     */
    private static double acceleration(double gradient, double coefficient) {
        double a = 0.0;
        a = 9.79 * (coefficient + (9.79 / gradient));
        return a;
    }

    private static Object[][] dataWithGradient(Hashtable accelTable, Object[][] trainData) {
        for (Object[] trainData1 : trainData) {
            for (int j = 0; j < trainData1.length; j += 7) {
                if (accelTable.containsKey(trainData1[j])) {
                    trainData1[j + 3] = accelTable.get(trainData1[j]);

                } else {

                }
            }
        }
        return trainData;
    }
}
