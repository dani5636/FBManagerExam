/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.BE.*;
import fbmanagerexam.GUI.Model.MatchModel;
import fbmanagerexam.GUI.Model.TeamModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class FinalViewController extends ParentController implements Initializable {

    @FXML
    private Label lblQ1Team1;
    @FXML
    private Label lblQ1Team2;
    @FXML
    private Label lblQ2Team1;
    @FXML
    private Label lblQ2Team2;
    @FXML
    private Label lblQ3Team1;
    @FXML
    private Label lblQ3Team2;
    @FXML
    private Label lblQ4Team1;
    @FXML
    private Label lblQ4Team2;
    @FXML
    private Label lblHeadline;
    @FXML
    private ImageView imgBracket;
    @FXML
    private Label lblS1Team1;
    @FXML
    private Label lblS1Team2;
    @FXML
    private Label lblS2Team1;
    @FXML
    private Label lblS2Team2;
    private Label lblFinal;
    @FXML
    private Label lblFinalTwo;
    @FXML
    private Label lblFinalOne;

    MatchModel matchModel = MatchModel.getMatchModel();
    @FXML
    private Button btnQ1;
    @FXML
    private Button btnQ2;
    @FXML
    private Button btnQ3;
    @FXML
    private Button btnQ4;
    @FXML
    private Button btnS1;
    @FXML
    private Button btnS2;
    @FXML
    private Button btnFinal;
    @FXML
    private Label lblFinalWinner;
    @FXML
    private Button btnPlacement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgBracket.setImage(new Image("file:Resources/Pictures/Bracket.png"));
        lblHeadline.setFont(Font.loadFont("file:Resources/Fonts/Enchanted Land.otf", 150));
        loadIntoView();
    }

    @FXML
    //uses the window loader to open up the match view, then it populates the match view with a certain match ID
    private void openMatch(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String[] toBeParsed = clicked.getText().split(": ");

        try {

            Stage primaryStage = (Stage) lblFinalOne.getScene().getWindow();
            FXMLLoader loader = super.windowLoader("/fbmanagerexam/GUI/View/MatchView.fxml", primaryStage);
            MatchViewController matchView = loader.getController();
            if (matchModel.doMatchExist(Integer.parseInt(toBeParsed[1]))) {
                matchView.populate(Integer.parseInt(toBeParsed[1]));
            }

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //closes the window
    @FXML
    private void closeWindow(ActionEvent event)//closes the window when the button is selected
    {
        Stage stage = (Stage) lblQ1Team1.getScene().getWindow();
        stage.close();
    }

    //runs all methods that updates labels,
    public void loadIntoView() {
        loadQuarterFinalIntoView();
        loadSemiFinalIntoView();
        loadFinalsIntoView();
    }

    //loads the quarterfinals into labels, if the quarterfinals exist
    private void loadQuarterFinalIntoView() {
        matchModel.updateQuarterFinals();
        Match quartOne = matchModel.getMatch(49);
        Match quartTwo = matchModel.getMatch(50);
        Match quartThree = matchModel.getMatch(51);
        Match quartFour = matchModel.getMatch(52);
        if (quartOne != null) {
            lblQ1Team1.setText(quartOne.getHomeTeamName());
            lblQ1Team2.setText(quartOne.getAwayTeamName());
        }
        if (quartTwo != null) {
            lblQ2Team1.setText(quartTwo.getHomeTeamName());
            lblQ2Team2.setText(quartTwo.getAwayTeamName());
        }
        if (quartThree != null) {
            lblQ3Team1.setText(quartThree.getHomeTeamName());
            lblQ3Team2.setText(quartThree.getAwayTeamName());
        }
        if (quartFour != null) {
            lblQ4Team1.setText(quartFour.getHomeTeamName());
            lblQ4Team2.setText(quartFour.getAwayTeamName());
        }
    }

    //loads the semifinals into labels, if the semifinals exist
    private void loadSemiFinalIntoView() {
        matchModel.updateSemiFinals();
        Match semiOne = matchModel.getMatch(53);
        Match semiTwo = matchModel.getMatch(54);
        if (semiOne != null) {
            lblS1Team1.setText(semiOne.getHomeTeamName());
            lblS1Team2.setText(semiOne.getAwayTeamName());
        }
        if (semiTwo != null) {
            lblS2Team1.setText(semiTwo.getHomeTeamName());
            lblS2Team2.setText(semiTwo.getAwayTeamName());
        }
    }

    //loads the finals into labels, if the finals exist
    private void loadFinalsIntoView() {
        matchModel.updateFinals();
        Match fMatch = matchModel.getMatch(55);
        if (fMatch != null) {
            lblFinalOne.setText(fMatch.getHomeTeamName());
            lblFinalTwo.setText(fMatch.getAwayTeamName());

            if (!fMatch.isUnplayed()) {
                lblFinalWinner.setText(fMatch.getWinner().getName());
            }
        }

    }

    @FXML
    //updates the lbls and if the final has been played it enables the placement button 
    private void updateFinalsonClick(ActionEvent event) {
        loadIntoView();
        if (matchModel.doMatchExist(55)) {
            if (!matchModel.getMatch(55).isUnplayed()) {
                TeamModel teamModel = TeamModel.getTeamModel();
                teamModel.updateFinalPlaceMent();
                btnPlacement.setDisable(false);
            }
        }
    }

    @FXML
    //opens the placement window using the windowloader
    private void openPlacement(ActionEvent event) {
        try {
            Stage currentStage = (Stage) btnPlacement.getScene().getWindow();
            super.windowLoader("/fbmanagerexam/GUI/View/PlacementView.fxml", currentStage);
        } catch (IOException ex) {
            Logger.getLogger(FinalViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
