/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.*;
import fbmanagerexam.GUI.Model.TeamModel;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Mecaa
 */
public class MainViewController extends ParentController implements Initializable {

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
    @FXML
    private Label lblRegTeam;

    private TeamModel teamModel = TeamModel.getTeamModel();

    private int teamId = 0;
    private int regTeam = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateFields();
    }

    @FXML
    private void startTournament(ActionEvent event) {
        teamModel.setTeamsIntoGroups();
        if (regTeam >= 12 && regTeam <= 16) {
            System.out.println("You may start the tournament");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Too few/many teams registered!");
            alert.setHeaderText("There is " + regTeam + " teams registered.");
            alert.setContentText("Add/remove teams to make it between 12 and 16.");
            alert.showAndWait();
        }
    }

    /* Opens a text input dialog when the button is pressed 
     *If there is a result present it splits the String into an array and
     * trims all the spaces away. It ups the teamId with one.
     * Then it generates a new team that is added to our team array for 
     * each String in the array.
     */
    @FXML
    private void addTeam(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Add A New Team");
        dialog.setHeaderText("Add a new team(s), Seperate with commas");
        dialog.setContentText("Please enter team name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            teamModel.addTeams(result.get());
            regTeam = teamModel.getTeams().size();
            lblRegTeam.setText("There is " + regTeam + " teams");

        }
    }

    /*
    *Opens up the teamview using the windowloader and sends the selected team 
    *to the Team view.
     */
    @FXML
    private void teamViewOpener(MouseEvent event) {

        //detect left-button double click
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            try {
                Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
                FXMLLoader loader = super.windowLoader("/fbmanagerexam/GUI/View/TeamView.fxml", primaryStage);
                TeamViewController TVController = loader.getController();
                Team sTeam = tblTeam.getSelectionModel().getSelectedItem();
                TVController.populateFields(sTeam);
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void updateTeam(ActionEvent event) {

    }

    //Uses the windowloader method to open the Group View when the button is pressed
    @FXML
    private void openGroup(ActionEvent event) {
        try {
            Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
            super.windowLoader("/fbmanagerexam/GUI/View/GroupView.fxml", primaryStage);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Uses the windowloader method to open the Final View when the button is pressed
    @FXML
    private void openFinal(ActionEvent event) {
        try {
            Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
            windowLoader("/fbmanagerexam/GUI/View/FinalView.fxml", primaryStage);

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void removeTeam(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm removing team");
        alert.setHeaderText("Are you sure you want to remove this team?");
        alert.setContentText("Are you sure you want to remove this team?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int index = tblTeam.getSelectionModel().getSelectedIndex();
            teamModel.removeTeam(index);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    /*Updates the fields of the team*/
    public void updateFields() {

        tblTeam.setItems(teamModel.getTeams());

        clmTeamId.setCellValueFactory(
                new PropertyValueFactory("id"));
        clmTeamGroup.setCellValueFactory(
                new PropertyValueFactory("group"));
        clmTeamName.setCellValueFactory(
                new PropertyValueFactory("name"));

    }

}
