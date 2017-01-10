/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.*;
import fbmanagerexam.GUI.Model.MatchModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gudla
 */
public class MatchViewController extends ParentController implements Initializable {

    @FXML
    private Button btnQuit;
    @FXML
    private Label lblMatchRound;
    @FXML
    private Label lblHTeamName;
    @FXML
    private Label lblATeamName;

    private MatchModel matchModel = MatchModel.getMatchModel();
    @FXML
    private TextField txtHTeamScore;
    @FXML
    private TextField txtATeamScore;
    @FXML
    private TextField txtMatchId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addListeners();

    }

    @FXML
    private void saveAndClose(ActionEvent event) {
        //needs to add the saving method here 
        closing();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        closing();
    }

    private void closing() {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }

    public void populate(int index) {
        Match match = matchModel.getMatches().get(index);
        txtMatchId.setText(Integer.toString(match.getMatchId()));
        lblATeamName.setText(match.getAwayTeamName());
        lblHTeamName.setText(match.getHomeTeamName());
        lblMatchRound.setText(Integer.toString(match.getRound()));
        txtATeamScore.setText(Integer.toString(match.getAwayScore()));
        txtHTeamScore.setText(Integer.toString(match.getHomeScore()));

    }
    //add change listeners to the text fields so you can only write numbers

    public void addListeners() {
        txtATeamScore.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (newValue.matches("\\d*") && newValue.length() < 3) {
                        int value = Integer.parseInt(newValue);
                    } else {
                        txtATeamScore.setText(oldValue);
                    }
                } catch (NumberFormatException ex) {
                    //do nothing
                }
            }

        });
        txtHTeamScore.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (newValue.matches("\\d*") && newValue.length() < 3) {
                        int value = Integer.parseInt(newValue);
                    } else {
                        txtHTeamScore.setText(oldValue);
                    }
                } catch (NumberFormatException ex) {
                    //do nothing
                }
            }

        });
        txtMatchId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (newValue.matches("\\d*") && newValue.length() < 3) {
                        int value = Integer.parseInt(newValue);
                    } else {
                        txtMatchId.setText(oldValue);
                    }
                } catch (NumberFormatException ex) {
                    //do nothing
                }
            }

        });
    }
}
