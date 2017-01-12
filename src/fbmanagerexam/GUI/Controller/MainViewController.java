/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.*;
import fbmanagerexam.GUI.Model.MatchModel;
import fbmanagerexam.GUI.Model.TeamModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
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
    private TableColumn<Team, String> clmTeamRank;
    @FXML
    private TableView<Match> tblMatch;
    @FXML
    private TableColumn<Match, String> clmMatchRound;
    @FXML
    private TableColumn<Match, String> clmMatchHTeam;
    @FXML
    private TableColumn<Match, String> clmMatchATeam;
    @FXML
    private TableColumn<Match, String> clmMatchGroup;
    @FXML
    private TableColumn<Match, String> clmMatchId;
    @FXML
    private Label lblRegTeam;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnSave;
    @FXML
    private Font x1;
    @FXML
    private Font x2;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnAddTeam;
    @FXML
    private Button btnUpdate;

    private TeamModel teamModel = TeamModel.getTeamModel();
    private MatchModel matchModel = MatchModel.getMatchModel();

    private int teamId = 0;
    private int regTeam = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateFields();

        //adds a listener to listen to changes in the team model
        teamModel.getTeams().addListener((ListChangeListener.Change< ? extends Team> c)
                -> {
            c.next();

            regTeam = teamModel.getTeams().size();
            lblRegTeam.setText("There is " + regTeam + " teams registered.");
        });
    }

    /*
    * Starts the tournament if there have been added 12-16 teams
    * if not enaugh teams or to many teams are entered then pops up an error saying that more teams are needed
    * when the tournament has started the buttons, add team, start tournament and update teams are disabled
    * the tables are refresed to show the right information
     */
    @FXML
    private void startTournament(ActionEvent event
    ) {

        if (regTeam >= 12 && regTeam <= 16) {
            System.out.println("You may start the tournament");
            teamModel.setTeamsIntoGroups();
            matchModel.generateMatches(teamModel.getAllGroups());
            tblTeam.refresh();
            tblMatch.refresh();
            btnLoad.setDisable(true);
            btnStart.setDisable(true);
            btnAddTeam.setDisable(true);
            btnUpdate.setDisable(true);
            btnSave.setDisable(false);

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Too few/many teams registered!");
            alert.setContentText("Add/remove teams to make it between 12 and 16.");
            alert.showAndWait();
        }
    }

    /* 
     *  Opens a text input dialog when the button is pressed 
     *  if there is a result present it splits the String into an array and
     * trims all the spaces away. It ups the teamId with one.
     * Then it generates a new team that is added to our team array for 
     * each String in the array.
     */
    @FXML
    private void addTeam(ActionEvent event
    ) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Add A New Team");
        dialog.setHeaderText("Add a new team(s), Seperate with commas");
        dialog.setContentText("Please enter team name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            teamModel.addTeams(result.get());
        }
    }

    /*
    *Opens up the teamview using the windowloader and sends the selected team 
    *to the Team view.
     */
    @FXML
    private void teamViewOpener(MouseEvent event
    ) {

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

    //Uses the windowloader method to open the Group View when the button is pressed
    @FXML
    private void openGroup(ActionEvent event
    ) {
        try {
            Stage primaryStage = (Stage) tblMatch.getScene().getWindow();

            super.windowLoader("/fbmanagerexam/GUI/View/GroupView.fxml", primaryStage);

        } catch (IOException ex) {

            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Uses the windowloader method to open the Final View when the button is pressed
    @FXML
    private void openFinal(ActionEvent event
    ) {
        try {
            Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
            windowLoader("/fbmanagerexam/GUI/View/FinalView.fxml", primaryStage);

        } catch (IOException ex) {

            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Shows a warning window when the button is pressed
    * if the user presses the OK button the team is removed
    * if the user closes the window or pressed cancel then nothing happens
     */
    @FXML
    private void removeTeam(ActionEvent event
    ) {
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

    /**
     * makes the MainView.fxml look for changes in the ObservableArraylists that
     * we are using in the controller.
     */
    public void updateFields() {

        tblTeam.setItems(teamModel.getTeams());

        clmTeamId.setCellValueFactory(
                new PropertyValueFactory("id"));
        clmTeamGroup.setCellValueFactory(
                new PropertyValueFactory("group"));
        clmTeamName.setCellValueFactory(
                new PropertyValueFactory("name"));
        clmTeamRank.setCellValueFactory(
                new PropertyValueFactory("rank"));

        tblMatch.setItems(matchModel.getMatches());
        clmMatchHTeam.setCellValueFactory(
                new PropertyValueFactory("homeTeamName"));
        clmMatchATeam.setCellValueFactory(
                new PropertyValueFactory("awayTeamName"));
        clmMatchGroup.setCellValueFactory(
                new PropertyValueFactory("group"));
        clmMatchRound.setCellValueFactory(
                new PropertyValueFactory("round"));
        clmMatchId.setCellValueFactory(
                new PropertyValueFactory("matchId"));
    }

    @FXML
    private void closeWindow(ActionEvent event) //Closes this window when the Quit button is selected 
    {
        Stage stage = (Stage) tblMatch.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveAll(ActionEvent event) {
        //Uses a filechooser to open dialog for save files 

        if (btnStart.isDisabled()) {
            FileChooser fileChooser = new FileChooser();
            Stage primaryStage = (Stage) btnSave.getScene().getWindow();
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new File("Data"));

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            matchModel.SaveTournament(file);
        }

    }

    @FXML
    private void Load(ActionEvent event) {
        //Uses a filechooser to open dialog to load saved files 
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = (Stage) btnLoad.getScene().getWindow();
        //Sets extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("Data"));
        //Shows save file dialog
        File file = fileChooser.showOpenDialog(primaryStage);

        matchModel.loadTournament(file);
        updateFields();
        btnStart.setDisable(true);
        btnLoad.setDisable(true);
        btnSave.setDisable(false);

    }

    @FXML
    private void matchViewOpener(MouseEvent event)//opens the match view when left-button is double clicked
    {
        //detect left-button double click
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            try {
                Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
                FXMLLoader loader = super.windowLoader("/fbmanagerexam/GUI/View/MatchView.fxml", primaryStage);
                MatchViewController MVController = loader.getController();
                int matchId;
                matchId = tblMatch.getSelectionModel().getSelectedItem().getMatchId();
                MVController.populate(matchId);

            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
    * Checks at first if there is something selected in the Team table
    * if not nothing happens
    * if there is something selected a Text input dialog window opens and the old name is in the text box
    * then it makes sure that there was something entered in the text box and then saves it instead of the old name
     */
    @FXML
    private void updateTeam(ActionEvent event) {

        if (!tblTeam.getSelectionModel().isEmpty()) {
            TextInputDialog dialog = new TextInputDialog(tblTeam.getSelectionModel().getSelectedItem().getName());
            dialog.setTitle("Edit this team");
            dialog.setHeaderText("Edit the name of this team");
            dialog.setContentText("Please enter team name:");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                tblTeam.getSelectionModel().getSelectedItem().setName(result.get());
                tblTeam.refresh();
            }
        }
    }

}
