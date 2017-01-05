/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
 * @author Mecaa
 */
public class TeamViewController implements Initializable {
    
    @FXML
    private Button btnExitTeamView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        //Uses the windowloader method to open the Group View when the button is pressed
    @FXML
    private void closeTeamView(ActionEvent event)
      {
        Stage stage = (Stage)btnExitTeamView.getScene().getWindow();
        stage.close();
      }
    private void windowloader(String p) throws IOException
      {
        Stage primaryStage = (Stage) btnExitTeamView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(p));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));

        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.initOwner(primaryStage);

        subStage.show();
      }
    
}
