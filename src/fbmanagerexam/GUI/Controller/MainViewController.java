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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Mecaa
 */
public class MainViewController implements Initializable {
    
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void startTournament(ActionEvent event) {
    }

    @FXML
    private void addTeam(ActionEvent event) {
    }

    @FXML
    private void updateTeam(ActionEvent event) {
        
    }
    
}
