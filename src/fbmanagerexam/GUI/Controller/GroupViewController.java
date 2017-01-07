/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class GroupViewController extends ParentController implements Initializable {
    
    private ChoiceBox <?> GroupChoicer;
    @FXML
    private ChoiceBox<String> CBgroups;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GroupChoicer();
    }  

    @FXML
    private void closeWindow(ActionEvent event)
      {
        Stage stage = (Stage) GroupChoicer.getScene().getWindow();
        stage.close();
      }

    
    
    
    
    private void GroupChoicer() {
        
    ObservableList<String> groups = FXCollections.observableArrayList("Group A", "Group B", "Group C", "Group D");
    CBgroups.setItems(groups);
    CBgroups.setValue("Group A");
    
    }
    
     
        
    }
