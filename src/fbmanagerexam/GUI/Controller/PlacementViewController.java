/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.Team;
import fbmanagerexam.GUI.Model.TeamModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mechaa
 */
public class PlacementViewController implements Initializable {
    
    @FXML
    private TableView<Team> tblRankedTeam;
    @FXML
    private TableColumn<Team, String> clmRank;
    @FXML
    private TableColumn<Team, String> clmTeamName;
    TeamModel teamModel = TeamModel.getTeamModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        tblRankedTeam.setItems(teamModel.getTeams());
        
        clmTeamName.setCellValueFactory(
                new PropertyValueFactory("name"));
        clmRank.setCellValueFactory(
                new PropertyValueFactory("rank"));
    }
    
    @FXML
    private void Quit(ActionEvent event) {
        
        Stage stage = (Stage) tblRankedTeam.getScene().getWindow();
        stage.close();
    }
    
}
