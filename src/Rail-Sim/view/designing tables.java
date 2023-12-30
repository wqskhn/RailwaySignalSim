package view;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import model.TrainData;
import static service.GradientCalc.readXlsxBook;

/**
 *
 * @author waqas
 */
public class AspectViewController implements Initializable {

    @FXML
    private TextField inpFle;

    @FXML
    private Button upLd;

    private List<String> lines;

    private Object[][] data;
    @FXML
    ObservableList<TrainData> values3 = FXCollections.observableArrayList();// data values for 3 aspect
    @FXML
    ObservableList<TrainData> values4 = FXCollections.observableArrayList();// data values for 4 aspect

    @FXML
    private TableView<TrainData> table1; //3 aspect
    @FXML
    private TableColumn<TrainData, String> t_Name;
    @FXML
    private TableColumn<TrainData, Double> t_Length;
    @FXML
    private TableColumn<TrainData, Double> t_Speed;
    @FXML
    private TableColumn<TrainData, Double> t_Deceleration;
    @FXML
    private TableColumn<TrainData, Double> overLap;
    @FXML
    private TableColumn<TrainData, Double> sighting;
    @FXML
    private TableColumn<TrainData, Double> signal_Spacing;
    @FXML
    private TableColumn<TrainData, Double> headway;

    // for 4 aspect table variables
    @FXML
    private TableView<TrainData> table2; //4 aspect
    @FXML
    private TableColumn<TrainData, String> t_Name2;
    @FXML
    private TableColumn<TrainData, Double> t_Length2;
    @FXML
    private TableColumn<TrainData, Double> t_Speed2;
    @FXML
    private TableColumn<TrainData, Double> t_Deceleration2;
    @FXML
    private TableColumn<TrainData, Double> overLap2;
    @FXML
    private TableColumn<TrainData, Double> sighting2;
    @FXML
    private TableColumn<TrainData, Double> signal_Spacing2;
    @FXML
    private TableColumn<TrainData, Double> headway2;

    /*
    Bar chart representation
     */
    @FXML
    private BarChart<String, Double> barChart; // 3 aspect bar chart

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private BarChart<String, Double> barChart1;//4 aspect bar chart
    @FXML
    private CategoryAxis xAxis1 = new CategoryAxis();

    public AspectViewController() {
        //  this.data = new ArrayList<>();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setup columns for 3 aspect table
        t_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        t_Length.setCellValueFactory(new PropertyValueFactory<>("length"));
        t_Speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        t_Deceleration.setCellValueFactory(new PropertyValueFactory<>("acceleration"));
        overLap.setCellValueFactory(new PropertyValueFactory<>("overlap"));
        sighting.setCellValueFactory(new PropertyValueFactory<>("sighting"));
        signal_Spacing.setCellValueFactory(new PropertyValueFactory<>("signalSpacing"));
        headway.setCellValueFactory(new PropertyValueFactory<>("headway"));
        //values = FXCollections.observableArrayList();
        table1.setItems(values3);

        // setup columns for 4 aspect table
        t_Name2.setCellValueFactory(new PropertyValueFactory<>("name"));
        t_Length2.setCellValueFactory(new PropertyValueFactory<>("length"));
        t_Speed2.setCellValueFactory(new PropertyValueFactory<>("speed"));
        t_Deceleration2.setCellValueFactory(new PropertyValueFactory<>("acceleration"));
        overLap2.setCellValueFactory(new PropertyValueFactory<>("overlap"));
        sighting2.setCellValueFactory(new PropertyValueFactory<>("sighting"));
        signal_Spacing2.setCellValueFactory(new PropertyValueFactory<>("signalSpacing"));
        headway2.setCellValueFactory(new PropertyValueFactory<>("headway"));

        table2.setItems(values4);

    }

    public void UploadAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data File", "*.xlsx"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            inpFle.setText(selectedFile.getAbsolutePath());

            // CsvReadWrite.readFile(selectedFile);//csv file
            data = readXlsxBook(selectedFile);

            this.displayData();
        } else {
            Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Excel file .");

            alert.showAndWait();
            // need to change to alert!!!!
        }

        
    }

    // display the data
    @FXML
    public void displayData() {
        for (Object[] data1 : data) {
            for (int j = 0; j < data1.length; j++) {
                String n = (String) data1[j];
                double l = (double) data1[j += 1];
                double v = (double) data1[j += 1];
                double a = (double) data1[j += 1];
                double o = (double) data1[j += 1];
                double s = (double) data1[j += 1];
                double d = (double) data1[j += 1];
                double h = (headwayCal(l, v, a, o, s, d));
                double h4 = (headwayCal4(l, v, a, o, s, d));
                TrainData value = new TrainData(n, l, v, a, o, s, d, h);
                TrainData test = new TrainData(n, l, v, a, o, s, d, h4);
                values3.add(value);
                values4.add(test);
            }
        }
        this.setPersonData1();
        this.setPersonData();

    }

    /*
        //Ht =1/v(2D+O+L)+St
        Ht is headway time
        V is train speed
        D is distance between signal
        O is overlap space for train
        L is train limit
        St is sight seening for train
     */
    private double headwayCal(double l, double v, double a, double o, double s, double d) {
        double h3;

        h3 = (((2 * d) + o + l) / v) + s;//warning action for speed
        return h3;
    }

    /*
        //Ht =1/v(1.5D+O+L)+St
        Ht is headway time
        V is train speed
        D is distance between signal
        O is overlap space for train
        L is train limit
        St is sight seening for train
     */
    private double headwayCal4(double l, double v, double a, double o, double s, double d) {
        double h4;

        h4 = (((1.5 * d) + o + l) / v) + s;// warning action
        return h4;
    }

    /**
     * Sets the persons to show the statistics for.
     *
     *  // int[] monthCounter = {23,4,56,7,88,9,0,43,56,2,4,67}; //Random r=new
     * Random(); //String alphabet ="123xyz";
     *
     */
    public void setPersonData() {

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        /*for (int i =0;i< monthCounter.length; i++){
          series.getData().add(new XYChart.Data<>(monthNames.get(i),monthCounter[i]));
      }*/
        for (int i = 0; i < values4.size(); i++) {
            series.getData().add(new XYChart.Data<>((values4.get(i).getName()), values4.get(i).getHeadway()));

        }
        barChart.getData().add(series);
    }

    /**
     * Sets the persons to show the statistics for.
     *
     *
     */
    public void setPersonData1() {
        // int[] monthCounter = {23,4,56,7,88,9,0,43,56,2,4,67};
        //Random r=new Random();
        //String alphabet ="123xyz";
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        /*for (int i =0;i< monthCounter.length; i++){
          series.getData().add(new XYChart.Data<>(monthNames.get(i),monthCounter[i]));
      }*/
        for (int i = 0; i < values3.size(); i++) {
            series.getData().add(new XYChart.Data<>((values3.get(i).getName()), values3.get(i).getHeadway()));

        }
        barChart1.getData().add(series);
    }
}
