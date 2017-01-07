/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gudla
 */
public class MatchViewController extends ParentController implements Initializable
{

    @FXML
    private Label lblMatch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
      }    

    @FXML
    private void saveAndClose(ActionEvent event)
      {
        //needs to add the saving method here 
        closing();
      }

    @FXML
    private void closeWindow(ActionEvent event)
      {
        closing();
      }
    
    private void closing()
      {
        Stage stage = (Stage) lblMatch.getScene().getWindow();
        stage.close();
      }
}
