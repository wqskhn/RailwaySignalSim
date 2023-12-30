package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.scene.control.Alert;
import static javafx.scene.input.DataFormat.URL;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

/**
 *
 * @author waqas
 */
public class IniReadWrite {

    /**
     * read the setting ini file from the system
     *
     * @return
     */
    public double readIni() {
        String gridentValue = null;
        try {
            Wini ini;

            URL turl;
            turl = getClass().getResource("/resource/setting.ini");
            ini = new Wini(turl);

            gridentValue = ini.get("gradient", "co-efficient");

        } catch (InvalidFileFormatException e) {
            // warning
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid file format.");

            alert.showAndWait();

        } catch (IOException e) {
            // warning
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Problem reading file" + e.toString());

            alert.showAndWait();

        }

        return Double.parseDouble(gridentValue);
    }

    /**
     * write the setting ini file
     *
     * @param args
     */
    public static void writeIni(String args[]) {
        Wini ini;
        try {
            ini = new Wini(new File("config/settings.ini"));
            ini.put("main", "window-color", 000000);
            ini.put("main", "splash", false);
            ini.store();
        } catch (InvalidFileFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid file format" + e.toString());

            alert.showAndWait();
            // warning
        } catch (IOException e) {
            // warning
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Problem reading file" + e.toString());

            alert.showAndWait();

        }

    }

}
