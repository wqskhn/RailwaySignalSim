package rail.plan;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Waqas Akhtar
 */
public class RailPlan extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Rail Plan");

        initRootLayout();

        //  showDataControlOverview();
        showAspectOverview();
    }

    public void initRootLayout() {
        try {
            // try catch bug

            //lood root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RailPlan.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    shows the Data control Overview
     */
    public void showAspectOverview() {
        try {
            //Load Data Control View
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RailPlan.class.getResource("/view/AspectView.fxml"));
            AnchorPane aspectOverView = (AnchorPane) loader.load();

            //set data control view into the Top of the root layout.
            rootLayout.setCenter(aspectOverView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    /**
     * Returns the main stage
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
