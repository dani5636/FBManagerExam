/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author gudla
 */
public class ParentController
{
    /**
    * Method used for window loading, returns the windowloader.
     */
    public FXMLLoader windowLoader(String p, Stage currentStage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(p));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));

        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.initOwner(currentStage);

        subStage.show();
        return loader;
    }
    
    
}

