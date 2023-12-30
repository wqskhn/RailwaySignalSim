package service;

/**
 *
 * @author waqas
 */
public class CsvParser {

    private Object lines;

    public void displayData() {
       // System.out.println(arrayList); This code should be activated if you need to parse csv file

        for (int i = 14; i < lines.size();) {

            String n = (lines.get(i));
            double l = (Double.parseDouble(lines.get(i + 1)));
            double v = (Double.parseDouble(lines.get(i + 2)));
            double a = (Double.parseDouble(lines.get(i + 3)));// affected with gradient
            double o = (Double.parseDouble(lines.get(i + 4)));
            double s = (Double.parseDouble(lines.get(i + 5)));
            double d = (Double.parseDouble(lines.get(i + 6)));//it's affected by acceleration
            //if g(i) is equal to grident then go to calulation find Grident then Brakedelay then follow below
            // otherwise follow below steps
            double h = (headwayCal(l, v, a, o, s, d));
            double h4 = (headwayCal4(l, v, a, o, s, d));
            TrainData value = new TrainData(n, l, v, a, o, s, d, h);
            TrainData test = new TrainData(n, l, v, a, o, s, d, h4);
            values3.add(value);
            values4.add(test);
            i = i + 7;

        }
        this.setPersonData1();
        this.setPersonData();*/

    }
}
