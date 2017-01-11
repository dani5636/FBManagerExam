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
<<<<<<< HEAD
public class MainViewController extends ParentController implements Initializable
{

=======
public class MainViewController extends ParentController implements Initializable {
    
>>>>>>> origin/master
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
    private TableColumn<Match, String> clmMatchRound;
    @FXML
    private TableColumn<Match, String> clmMatchHTeam;
    @FXML
    private TableColumn<Match, String> clmMatchATeam;
    @FXML
    private TableColumn<Match, String> clmMatchGroup;
    @FXML
    private Label lblRegTeam;
    
    private TeamModel teamModel = TeamModel.getTeamModel();
    private MatchModel matchModel = MatchModel.getMatchModel();
    
    private int teamId = 0;
    private int regTeam = 0;
    @FXML
    private Button btnStart;
    @FXML
    private TableColumn<?, ?> clmMatchId;
    @FXML
    private Button btnSave;
    @FXML
    private Font x1;
    @FXML
    private Font x2;
    @FXML
    private Button btnLoad;
<<<<<<< HEAD
    @FXML
    private Button btnAddTeam;
    @FXML
    private Button btnUpdate;

=======
    
>>>>>>> origin/master
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        updateFields();
<<<<<<< HEAD

      }

    @FXML

    private void startTournament(ActionEvent event)
      {

        if (regTeam >= 12 && regTeam <= 16)
          {
=======
        teamModel.getTeams().addListener((ListChangeListener.Change< ? extends Team> c) -> {
            c.next();
            
            regTeam = teamModel.getTeams().size();
            lblRegTeam.setText("There is " + regTeam + " teams registered.");
        });
        btnSave.setDisable(true);
    }
    
    @FXML
    
    private void startTournament(ActionEvent event) {
        
        if (regTeam >= 12 && regTeam <= 16) {
>>>>>>> origin/master
            System.out.println("You may start the tournament");
            teamModel.setTeamsIntoGroups();
            matchModel.generateMatches(teamModel.getAllGroups());
            tblTeam.refresh();
            tblMatch.refresh();
            btnLoad.setDisable(true);
            btnStart.setDisable(true);
<<<<<<< HEAD
            btnAddTeam.setDisable(true);
            btnUpdate.setDisable(true);

          } else
          {
=======
            btnSave.setDisable(false);
            
        } else {
>>>>>>> origin/master
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Too few/many teams registered!");
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
    private void addTeam(ActionEvent event)
      {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Add A New Team");
        dialog.setHeaderText("Add a new team(s), Seperate with commas");
        dialog.setContentText("Please enter team name:");
        
        Optional<String> result = dialog.showAndWait();
<<<<<<< HEAD

        if (result.isPresent())
          {
            teamModel.addTeams(result.get());
            regTeam = teamModel.getTeams().size();
            lblRegTeam.setText("There is " + regTeam + " teams");

          }
      }
=======
        
        if (result.isPresent()) {
            teamModel.addTeams(result.get());
        }
    }
>>>>>>> origin/master

    /*
    *Opens up the teamview using the windowloader and sends the selected team 
    *to the Team view.
     */
    @FXML
    private void teamViewOpener(MouseEvent event)
      {

        //detect left-button double click
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
          {
            try
              {
                Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
                FXMLLoader loader = super.windowLoader("/fbmanagerexam/GUI/View/TeamView.fxml", primaryStage);
                TeamViewController TVController = loader.getController();
                Team sTeam = tblTeam.getSelectionModel().getSelectedItem();
                TVController.populateFields(sTeam);
              } catch (IOException ex)
              {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      }

    //Uses the windowloader method to open the Group View when the button is pressed
    @FXML
    private void openGroup(ActionEvent event)
      {
        try
          {
            Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
            
            super.windowLoader("/fbmanagerexam/GUI/View/GroupView.fxml", primaryStage);
<<<<<<< HEAD
          } catch (IOException ex)
          {

=======
        } catch (IOException ex) {
            
>>>>>>> origin/master
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

    //Uses the windowloader method to open the Final View when the button is pressed
    @FXML
    private void openFinal(ActionEvent event)
      {
        try
          {
            Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
            windowLoader("/fbmanagerexam/GUI/View/FinalView.fxml", primaryStage);
<<<<<<< HEAD

          } catch (IOException ex)
          {

            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

=======
            
        } catch (IOException ex) {
            
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
>>>>>>> origin/master
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
            int index = tblTeam.getSelectionModel().getSelectedIndex();
            teamModel.removeTeam(index);
<<<<<<< HEAD

          } else
          {
=======
            
        } else {
>>>>>>> origin/master
            // ... user chose CANCEL or closed the dialog
          }
      }

    /*makes the MainView.fxml look for changes in the ObservableArraylists that
    * we are using in the controller.
     */
<<<<<<< HEAD
    public void updateFields()
      {

=======
    public void updateFields() {
        
>>>>>>> origin/master
        tblTeam.setItems(teamModel.getTeams());
        
        clmTeamId.setCellValueFactory(
                new PropertyValueFactory("id"));
        clmTeamGroup.setCellValueFactory(
                new PropertyValueFactory("group"));
        clmTeamName.setCellValueFactory(
                new PropertyValueFactory("name"));
        
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
<<<<<<< HEAD
      }

=======
    }
    
>>>>>>> origin/master
    @FXML
    private void closeWindow(ActionEvent event)
      {
        Stage stage = (Stage) tblMatch.getScene().getWindow();
        stage.close();
<<<<<<< HEAD
      }

=======
    }
    
>>>>>>> origin/master
    @FXML
    private void saveAll(ActionEvent event)
      {
        //Use a filechooser to open dialog for save files 
<<<<<<< HEAD
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

=======
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
    
>>>>>>> origin/master
    @FXML
    private void Load(ActionEvent event)
      {
        //Use a filechooser to open dialog to load saved files 
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = (Stage) btnLoad.getScene().getWindow();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("Data"));
        //Show save file dialog
        File file = fileChooser.showOpenDialog(primaryStage);
<<<<<<< HEAD

      }

=======
        
        matchModel.loadTournament(file);
        updateFields();
        btnStart.setDisable(true);
        btnLoad.setDisable(true);
        btnSave.setDisable(false);
        
    }
    
>>>>>>> origin/master
    @FXML
    private void matchViewOpener(MouseEvent event)
      {
        //detect left-button double click
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
          {
            try
              {
                Stage primaryStage = (Stage) tblMatch.getScene().getWindow();
                FXMLLoader loader = super.windowLoader("/fbmanagerexam/GUI/View/MatchView.fxml", primaryStage);
                MatchViewController MVController = loader.getController();
                int matchId;
                matchId = tblMatch.getSelectionModel().getSelectedItem().getMatchId();
                MVController.populate(matchId);
              } catch (IOException ex)
              {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
<<<<<<< HEAD
              }
          }
      }

    @FXML
    private void updateTeam(ActionEvent event)
      {

        if (!tblTeam.getSelectionModel().isEmpty())
          {
            TextInputDialog dialog = new TextInputDialog(tblTeam.getSelectionModel().getSelectedItem().getName());
            dialog.setTitle("Edit this team");
            dialog.setHeaderText("Edit the name of this team");
            dialog.setContentText("Please enter team name:");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent())
              {
                tblTeam.getSelectionModel().getSelectedItem().setName(result.get());
                tblTeam.refresh();
              }
          }
      }

=======
            }
        }
    }
    
    @FXML
    private void updateTeam(ActionEvent event) {
        
    }
    
>>>>>>> origin/master
}
