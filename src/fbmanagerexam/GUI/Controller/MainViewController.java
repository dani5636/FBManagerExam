/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mecaa
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<Team> tblTeam;
    @FXML
    private TableColumn<Team, String> clmTeamId;
    @FXML
    private TableColumn<Team, String> clmTeamGroup;
    @FXML
    private TableColumn<Team, String> clmTeamName;
    @FXML
    private TableView<Match> tblMatch;
    @FXML
    private TableColumn<Match, ?> clmMatchRound;
    @FXML
    private TableColumn<?, ?> clmMatchHTeam;
    @FXML
    private TableColumn<?, ?> clmMatchATeam;

    private List<Team> teams = new ArrayList<Team>();
    private int teamId = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void startTournament(ActionEvent event) {
    }

    // Opens a text input dialog when the button is pressed 
    @FXML
    private void addTeam(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Add A New Team");
        dialog.setHeaderText("Add a new team(s), Seperate with commas");
        dialog.setContentText("Please enter team name:");

        Optional<String> result = dialog.showAndWait();
        /*If there is a result present it splits the String into an array and
        * trims all the spaces away. It ups the teamId with one.
        * Then it generates a new team that is added to our team array for 
        * each String in the array.
         */
        if (result.isPresent()) {
            String[] allTeams = result.get().split(",");
            for (String allTeam : allTeams) {
                allTeam = allTeam.trim();
                teamId++;
                teams.add(new Team(teamId, allTeam));
            }
            updateFields();
        }
    }

    @FXML
    private void updateTeam(ActionEvent event) {

    }

    //Uses the windowloader method to open the Group View when the button is pressed
    @FXML
    private void openGroup(ActionEvent event) {
        try {
            windowloader("/fbmanagerexam/GUI/View/GroupView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Uses the windowloader method to open the Final View when the button is pressed
    @FXML
    private void openFinal(ActionEvent event) {
        try {
            windowloader("/fbmanagerexam/GUI/View/FinalView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Meathod used for window loading
     */
    private void windowloader(String p) throws IOException {
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
    private void removeTeam(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm removing team");
        alert.setHeaderText("Are you sure you want to remove this team?");
        alert.setContentText("Are you sure you want to remove this team?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    /*Updates the fields of the team*/
    public void updateFields() {
        if (!teams.isEmpty()) {

            ObservableList<Team> AllTeams
                    = FXCollections.observableArrayList();
            AllTeams.addAll(teams);
            tblTeam.setItems(AllTeams);

            clmTeamId.setCellValueFactory(
                    new PropertyValueFactory("id"));
            clmTeamGroup.setCellValueFactory(
                    new PropertyValueFactory("group"));
            clmTeamName.setCellValueFactory(
                    new PropertyValueFactory("name"));

        }
    }
}
