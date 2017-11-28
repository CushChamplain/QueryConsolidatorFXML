package queryconsolidatorfxml;

//imports
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
 * @Subclass SceneController Description: class used to to load a new scene
 * on top of the stage or create a new stage and load the scene on there
 */

//Begin Subclass SceneController
public class SceneController {

    /**
     * Constructor
     */
    public SceneController() {

    }

    Parent root; //Create a Parent node to use later

    /**
     * This method is used when program wants to switch between scenes on the
     * same stage. It takes a reference to the current stage so it can load the
     * new scene. Note- two argument method.
     *
     * @param stage
     * @param fxml
     */
    public void setScene(Stage stage, String fxml) {

        try {
            //Create the FXMLLoader
            FXMLLoader theLoader = new FXMLLoader(getClass().getResource(fxml));
            
            root = theLoader.load();  //Call load method and assign to root

        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root); //Create the Scene and add FXML to it
        stage.setScene(scene); //Set the scene to the reference to stage passed
        stage.show(); //Show the scene

    }

    /**
     * This method is used when program is going to create a new stage and scene
     * that will lay over the top of the current stage and scene. Note: one 
     * argument method
     * @param fxml
     */
    public void setScene(String fxml) {

        Stage newStage = new Stage();
        try {
            //Create the FXMLLoader
            FXMLLoader theLoader = new FXMLLoader(getClass().getResource(fxml));
            
            root = theLoader.load(); //Call load method to assign to root

        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root); //Create the Scene and add FXML to it
        newStage.setScene(scene); //This is where it differs from 2 arg method (newStage)
        newStage.show(); //This is where it differs from 2 arg method (newStage)

    }
} //End Subclass SceneController
