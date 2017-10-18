package queryconsolidatorfxml;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cush
 */
public class ClientUsageController implements Initializable {

    Stage stage; //Create a stage to use later, will be a reference to current stage.
    
    //Create instance of SceneController to user below
    SceneController sceneController = new SceneController();
    

    @FXML
    private Button clientUsageBackBtn;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void clientUsageBack(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println(QueryConsolidatorFXML.getServer());

        //Get reference to the Stage the current scene is on (only 1 in this program)
        stage = (Stage) clientUsageBackBtn.getScene().getWindow();
        
        //Call SceneController method to set the scene
        sceneController.setScene(stage, QueryConsolidatorFXML.getMainFXML());
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
