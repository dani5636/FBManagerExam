/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.*;
import fbmanagerexam.GUI.Model.MatchModel;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class TeamViewController extends ParentController implements Initializable {

    @FXML
    private Button btnExitTeamView;
    @FXML
    private TableView<Match> tblTeamMatch;
    @FXML
    private Label lblId, lblGDiff, lblPoints, lblName;
    private MatchModel matchModel = MatchModel.getMatchModel();
    @FXML
    private TableColumn<Match, String> clmRound;
    @FXML
    private TableColumn<Match, String> clmHomeTeam;
    @FXML
    private TableColumn<Match, String> clmAwayTeam;
    @FXML
    private Font x1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateView();
    }
    //Uses the windowloader method to open the Group View when the button is pressed

    @FXML
    private void closeTeamView(ActionEvent event) {
        Stage stage = (Stage) btnExitTeamView.getScene().getWindow();
        stage.close();
    }

    //Populates the label with the team that has been given.
    public void populateFields(Team sTeam) {
        lblId.setText(sTeam.getId() + "");
        lblGDiff.setText(sTeam.getgDiff() + "");
        lblPoints.setText(sTeam.getPoint() + "");
        lblName.setText(sTeam.getName());
        matchModel.setTeamMatches(sTeam.getId());
    }

    public void updateView() {
        tblTeamMatch.setItems(matchModel.getTeamMatches());
        clmHomeTeam.setCellValueFactory(
                new PropertyValueFactory("homeTeamName"));
        clmAwayTeam.setCellValueFactory(
                new PropertyValueFactory("awayTeamName"));
        clmRound.setCellValueFactory(
                new PropertyValueFactory("round"));
    }

}
