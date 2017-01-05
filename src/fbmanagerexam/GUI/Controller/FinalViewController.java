/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class FinalViewController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgBracket.setImage(new Image("file:Resources/Pictures/Bracket.png"));
        lblHeadline.setFont(Font.loadFont("file:Resources/Fonts/Enchanted Land.otf", 150));
    }

}
