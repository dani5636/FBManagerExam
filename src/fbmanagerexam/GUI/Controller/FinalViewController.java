/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import fbmanagerexam.GUI.Model.MatchModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Label lblS2Team1;
    @FXML
    private Label lblS2Team2;
    @FXML
    private Label lblQ1Team11211;
    @FXML
    private Label lblFinal;
    @FXML
    private Label lblQ1Team1131;

    public int Q1 = 49;
    MatchModel matchModel = MatchModel.getMatchModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgBracket.setImage(new Image("file:Resources/Pictures/Bracket.png"));
        lblHeadline.setFont(Font.loadFont("file:Resources/Fonts/Enchanted Land.otf", 150));
        loadQuarterFinal();
    }

    @FXML
    private void openTeam(ActionEvent event) //uses the window loader to open up the team view
    {
        try {
            Stage primaryStage = (Stage) lblFinal.getScene().getWindow();
            super.windowLoader("/fbmanagerexam/GUI/View/TeamView.fxml", primaryStage);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadQuarterFinal() {
        matchModel.updateQuarterFinals();
        if (matchModel.getMatch(Q1) != null) {
            lblQ1Team1.setText(matchModel.getMatch(Q1).getHomeTeamName());
            lblQ1Team2.setText(matchModel.getMatch(Q1).getAwayTeamName());
        }

        if (matchModel.getMatch(Q1 + 1) != null) {
            lblQ2Team1.setText(matchModel.getMatch(Q1 + 1).getHomeTeamName());
            lblQ2Team2.setText(matchModel.getMatch(Q1 + 1).getHomeTeamName());
        }
        if (matchModel.getMatch(Q1 + 2) != null) {
            lblQ2Team1.setText(matchModel.getMatch(Q1 + 2).getHomeTeamName());
            lblQ2Team2.setText(matchModel.getMatch(Q1 + 2).getHomeTeamName());
        }
        if (matchModel.getMatch(Q1 + 3) != null) {
            lblQ2Team1.setText(matchModel.getMatch(Q1 + 3).getHomeTeamName());
            lblQ2Team2.setText(matchModel.getMatch(Q1 + 3).getHomeTeamName());
        }
    }

    @FXML
    private void closeWindow(ActionEvent event)//closes the window when the button is selected
    {
        Stage stage = (Stage) lblQ1Team1.getScene().getWindow();
        stage.close();
    }

}
