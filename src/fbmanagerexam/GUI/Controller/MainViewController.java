/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mecaa
 */
public class MainViewController implements Initializable
{

    @FXML
    private TableView<?> tblTeam;
    @FXML
    private TableColumn<?, ?> clmTeamId;
    @FXML
    private TableColumn<?, ?> clmTeamGroup;
    @FXML
    private TableColumn<?, ?> clmTeamName;
    @FXML
    private TableView<?> tblMatch;
    @FXML
    private TableColumn<?, ?> clmMatchRound;
    @FXML
    private TableColumn<?, ?> clmMatchHTeam;
    @FXML
    private TableColumn<?, ?> clmMatchATeam;

    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
      }

    @FXML
    private void startTournament(ActionEvent event)
      {
      }

    // Opens a text input dialog when the button is pressed
    @FXML
    private void addTeam(ActionEvent event)
      {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Add A New Team");
        dialog.setHeaderText("Add A New Team");
        dialog.setContentText("Please enter team name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
          {
            System.out.println("Team name: " + result.get());
          }
      }

    @FXML
    private void updateTeam(ActionEvent event)
      {

      }

    //Uses the windowloader method to open the Group View when the button is pressed
    @FXML
    private void openGroup(ActionEvent event)
      {
        try
          {
            windowloader("/fbmanagerexam/GUI/View/GroupView.fxml");
          } catch (IOException ex)
          {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

    //Uses the windowloader method to open the Final View when the button is pressed
    @FXML
    private void openFinal(ActionEvent event)
      {
        try
          {
            windowloader("/fbmanagerexam/GUI/View/FinalView.fxml");
          } catch (IOException ex)
          {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

    /*
    Meathod used for window loading
     */
    private void windowloader(String p) throws IOException
      {
        Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(p));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));

        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.initOwner(primaryStage);

        subStage.show();
      }

    @FXML
    private void removeTeam(ActionEvent event)
      {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm removing team");
        alert.setHeaderText("Are you sure you want to remove this team?");
        alert.setContentText("Are you sure you want to remove this team?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
          {
            // ... user chose OK
          } else
          {
            // ... user chose CANCEL or closed the dialog
          }
      }

    
}
