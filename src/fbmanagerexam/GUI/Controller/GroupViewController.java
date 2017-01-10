/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.Match;
import fbmanagerexam.BE.Team;
import fbmanagerexam.GUI.Model.MatchModel;
import fbmanagerexam.GUI.Model.TeamModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class GroupViewController extends ParentController implements Initializable
{

    @FXML
    private ChoiceBox<String> CBgroups;
    @FXML
    private TableColumn<Team, String> clmTeamID;
    @FXML
    private TableColumn<Team, String> clmTeamName;
    @FXML
    private TableColumn<Team, String> clmRank;
    @FXML
    private TableColumn<Match, String> clmMatchID;
    @FXML
    private TableColumn<Match, String> clmMatchHTeam;
    @FXML
    private TableColumn<Match, String> clmMatchATeam;
    @FXML
    private TableView<Team> tblTeam;
    @FXML
    private TableView<Match> tblMatch;

    private TeamModel teamModel = TeamModel.getTeamModel();
    private MatchModel matchModel = MatchModel.getMatchModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
        GroupChoicer();
        matchModel.setGroupMatches(CBgroups.getSelectionModel().getSelectedItem());
        
        
        
      }

    @FXML
    private void closeWindow(ActionEvent event)
      {
        Stage stage = (Stage) CBgroups.getScene().getWindow();
        stage.close();
      }

    private void GroupChoicer()
      {

        ObservableList<String> groups = FXCollections.observableArrayList("Group A", "Group B", "Group C", "Group D");
        CBgroups.setItems(groups);
        CBgroups.setValue("Group A");
        checkWhichGroup();

      }

   private void updateFields()
      {
        
        clmTeamID.setCellValueFactory(
                new PropertyValueFactory("id"));
        clmRank.setCellValueFactory(
                new PropertyValueFactory("point"));
        clmTeamName.setCellValueFactory(
                new PropertyValueFactory("name"));

        tblMatch.setItems(matchModel.getGroupMatches());
        clmMatchHTeam.setCellValueFactory(
                new PropertyValueFactory("homeTeamName"));
        clmMatchATeam.setCellValueFactory(
                new PropertyValueFactory("awayTeamName"));
        clmMatchID.setCellValueFactory(
                new PropertyValueFactory("matchId"));
      }
    
    
   //a,b,c,d,e,f,g,h,i,j,k,l
   
   private void checkWhichGroup()
     {
            
       if (CBgroups.getSelectionModel().isSelected(0))
         {
           tblTeam.setItems(teamModel.getAllGroups().get(0));
           updateFields();
           tblTeam.refresh();
           
         } else if (CBgroups.getSelectionModel().isSelected(1))
           {
             tblTeam.setItems(teamModel.getAllGroups().get(1));
             updateFields();
             tblTeam.refresh();
           }
       else if (CBgroups.getSelectionModel().isSelected(2))
           {
             tblTeam.setItems(teamModel.getAllGroups().get(2));
             updateFields();
             tblTeam.refresh();
           }
       else if (CBgroups.getSelectionModel().isSelected(3))
           {
             tblTeam.setItems(teamModel.getAllGroups().get(3));
             updateFields();
             tblTeam.refresh();
           }
     }

    

    
}
