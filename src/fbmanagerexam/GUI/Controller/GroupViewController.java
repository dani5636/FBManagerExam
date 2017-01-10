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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
          
        groupChoicer();
        updateFields();
        addListener();
        
      }

    @FXML
    private void closeWindow(ActionEvent event)
      {
        Stage stage = (Stage) CBgroups.getScene().getWindow();
        stage.close();
      }

    private void groupChoicer()
      {

        ObservableList<String> groups = FXCollections.observableArrayList("Group A", "Group B", "Group C", "Group D");
        CBgroups.setItems(groups);
        CBgroups.setValue("Group A");
        

      }

   private void updateFields()
      {
        tblTeam.setItems(teamModel.getAllGroups().get(CBgroups.getSelectionModel().getSelectedIndex()));
        clmTeamID.setCellValueFactory(
                new PropertyValueFactory("id"));
        clmRank.setCellValueFactory(
                new PropertyValueFactory("point"));
        clmTeamName.setCellValueFactory(
                new PropertyValueFactory("name"));

        matchModel.setGroupMatches(CBgroups.getSelectionModel().getSelectedItem());
               
        tblMatch.setItems(matchModel.getGroupMatches());
        clmMatchHTeam.setCellValueFactory(
                new PropertyValueFactory("homeTeamName"));
        clmMatchATeam.setCellValueFactory(
                new PropertyValueFactory("awayTeamName"));
        clmMatchID.setCellValueFactory(
                new PropertyValueFactory("matchId"));
      }
    
    
   //a,b,c,d,e,f,g,h,i,j,k,l
   
   
  

    public void addListener()
      {
        CBgroups.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed (ObservableValue ov, Number value, Number new_value) 
              {
                tblTeam.setItems(teamModel.getAllGroups().get(CBgroups.getSelectionModel().getSelectedIndex()));
                matchModel.setGroupMatches(CBgroups.getSelectionModel().getSelectedItem());
                //updateFields();
                tblTeam.refresh();
                tblMatch.refresh();
              }
                    
        });
      }
        
      

    
}
