package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 10, 2017
 * @Description: This class is the controller for the UserInfo.fxml of the U/I
 * The user info is the scene that appears when selecting option 3 from the main
 * scene. This class extends MainSceneController class in order to use a Stage
 * variable, Verify & SceneController instances.
 * @Reference:
 */
//Imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class extends MainSceneController
 *
 * @author Cush
 */
public class UserInfoController extends MainSceneController implements Initializable {

    @FXML
    private Button btnBackUserInfo; //Used to get reference to stage and that's it.

    //Declare the FXML fields
    @FXML
    Label lblActive;
    @FXML
    Label lblInactive;
    @FXML
    private TextField tfActive;
    @FXML
    private TextField tfInactive;
    @FXML
    private TextArea taNote;

    /**
     * actionBackUserInfo method: ActionEvent by user to go back to main
     */
    @FXML
    private void actionBackUserInfo(ActionEvent event) {


        /*Get reference to the Stage the current scene is on
          Note stage variable is inherited from MainSceneController class
        */
        stage = (Stage) btnBackUserInfo.getScene().getWindow();

        /*Call SceneController instance method to swap scenes.  
          Note this instance is inherited from MainSceneController class.
        */
        sceneController.setScene(stage, QueryConsolidatorFXML.getMainFXML());

    }

    /**
     * actionExitUserInfo method: ActionEvent by user to exit program
     */
    @FXML
    private void actionExitUserInfo(ActionEvent event) {

        System.exit(0);

    }

    /**
     * actionSelectActive method: ActionEvent by user selecting to pull data
     * from database based on fields entered (or not entered)
     */
    @FXML
    private void actionSelectActive(ActionEvent event) {

        boolean check = true;

        //Clear the labels to reset
        lblActive.setText("");
        lblInactive.setText("");

        tfInactive.clear(); //Clear TextField not relevant to action

        //If they entered a client code, check that it's valid
        if (!tfActive.getText().isEmpty() && !verify.isClient(tfActive)) {

            lblActive.setText("Invalid code");
            tfActive.requestFocus();
            check = false;

        }

        if (check) {

            /*Get focus for after display so user can see what they entered
              last and just start typing to enter a different code
            */
            tfActive.requestFocus();

            //Set the global client code variable
            QueryConsolidatorFXML.setClientCode(tfActive.getText());

            //Set the gobal user status variable, will be used for queries
            QueryConsolidatorFXML.setUserStatus("active");

            /*Call SceneController instance method with 1 argument to lay on top
              of weekly run info screen (Not take the stage currently there).
              Note this instance is inherited from MainSceneController class.
            */
            sceneController.setScene(QueryConsolidatorFXML.getUserInfoTableFXML());

        }

    }

    /**
     * actionSelectInactive method: ActionEvent by user selecting to pull data
     * from database based on fields entered (or not entered)
     */
    @FXML
    private void actionSelectInactive(ActionEvent event) {

        boolean check = true;

        //Clear the labels to reset
        lblActive.setText("");
        lblInactive.setText("");

        tfActive.clear(); //Clear TextField not relevant to action

        //If they entered a client code, check that it's valid
        if (!tfInactive.getText().isEmpty() && !verify.isClient(tfInactive)) {

            lblInactive.setText("Invalid code");
            tfInactive.requestFocus();
            check = false;

        }

        if (check) {

            /*Get focus for after display so user can see what they entered
              last and just start typing to enter a different code
            */
            tfInactive.requestFocus();

            //Set the global client code variable
            QueryConsolidatorFXML.setClientCode(tfInactive.getText());

            //Set the gobal user status variable, will be used for queries
            QueryConsolidatorFXML.setUserStatus("inactive");

            /*Call SceneController instance method with 1 argument to lay on top
              of weekly run info screen (Not take the stage currently there).
              Note this instance is inherited from MainSceneController class.
            */
            sceneController.setScene(QueryConsolidatorFXML.getUserInfoTableFXML());

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
    }

}
