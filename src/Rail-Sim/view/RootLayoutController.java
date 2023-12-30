
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author waqas
 */
public class RootLayoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
        private Stage stage;
        
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    /**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handlePdf() throws IOException {
    

 
}
@FXML
private void handleEmail() {
 
}
@FXML
private void handlePrint() {
 
}
@FXML
private void handleSettings() throws IOException {

   Parent root;
   stage = new Stage();
   root = FXMLLoader.load(getClass().getResource("/view/SettingLayout.fxml"));
   stage.setScene(new Scene(root));
   stage.setTitle("Setting");
   stage.initModality(Modality.APPLICATION_MODAL);
  stage.showAndWait();
 
}

@FXML
private void handleHelp() throws IOException {

   Parent root;
   stage = new Stage();
   root = FXMLLoader.load(getClass().getResource("/view/Help.fxml"));
   stage.setScene(new Scene(root));
   stage.setTitle("Help");
   stage.initModality(Modality.APPLICATION_MODAL);
  stage.showAndWait();
 
}

@FXML
private void handleAbout() throws IOException {

   Parent root;
   stage = new Stage();
   root = FXMLLoader.load(getClass().getResource("/view/About.fxml"));
   stage.setScene(new Scene(root));
   stage.setTitle("About");
   stage.initModality(Modality.APPLICATION_MODAL);
  stage.showAndWait();
 
}
}
