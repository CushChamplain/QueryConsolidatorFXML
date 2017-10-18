package queryconsolidatorfxml;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 14, 2017
 * @Subclass SceneController Description: 
 */
//Imports

//Begin Subclass SceneController
public class SceneController {

    /**
     * Constructor
     */
    public SceneController() {
        
        
    }
    
    Parent root;
    
    public void setScene (Stage stage, String fxml) {
        
        try {
                //Load the Client Usage FXML.  Done in two steps so I can get 
                //The FXML loader (theLoader) name in order to get getController()
                //to me 
                FXMLLoader theLoader = new FXMLLoader(getClass().getResource(fxml));
                root = theLoader.load();


            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();

        
    }
} //End Subclass SceneController