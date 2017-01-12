/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.*;
import fbmanagerexam.GUI.Model.*;
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
    Match match = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addListener();

    }

    /**
     * sets winner and saves ten it closes the window using the close method
     */
    @FXML
    private void saveAndClose(ActionEvent event) {
        if (match.isUnplayed()) {
            match.setWinner(
                    Integer.parseInt(txtHTeamScore.getText()),
                    Integer.parseInt(txtATeamScore.getText()));
            TeamModel.getTeamModel().updateGroupRanking();
            matchModel.updateSemiFinals();
        }
        closing();
    }

    @FXML
    private void closeWindow(ActionEvent event) //uses the closing method to close the window when thee button is pressed
    {
        closing();
    }

    private void closing() //a method to close the window
    {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }

    public void populate(int matchId) //sets the lables to show the right information
    {
        match = matchModel.getMatch(matchId);

        txtATeamScore.setDisable(false);
        txtHTeamScore.setDisable(false);
        txtMatchId.setText(Integer.toString(match.getMatchId()));
        lblATeamName.setText(match.getAwayTeamName());
        lblHTeamName.setText(match.getHomeTeamName());
        lblMatchRound.setText(Integer.toString(match.getRound()));
        txtATeamScore.setText(Integer.toString(match.getAwayScore()));
        txtHTeamScore.setText(Integer.toString(match.getHomeScore()));
        if (!match.isUnplayed()) {
            txtATeamScore.setDisable(true);
            txtHTeamScore.setDisable(true);
        }

    }

    //adds change listeners to the text fields so you can only write numbers
    public void addListener() {
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
                    if (newValue.matches("\\d*")
                            && newValue.length() < 3
                            && matchModel.getMatch(Integer.parseInt(newValue)) != null) {
                        int value = Integer.parseInt(newValue);
                        populate(value);

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
