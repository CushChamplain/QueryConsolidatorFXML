package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description: This class is the controller for the ClientUsage.fxml of the
 * U/I The client usage is the scene that appears when selecting option 1 from
 * the main scene.  This class extends MainSceneController class in order to use
 * a Stage variable, Verify & SceneController instances.
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
public class ClientUsageController extends MainSceneController implements Initializable {

    @FXML
    private Button btnBackClientUsage; //Used to get reference to stage and that's it.

    //Delare the FXML fields
    @FXML
    Label lblSelect; 
    @FXML
    private TextField tfStartDate;
    @FXML
    private TextField tfEndDate;
    @FXML
    private TextField tfClient;
    @FXML
    private TextArea taNote;

    /**
     * actionBackClientUsage method: ActionEvent by user to go back to main
     */
    @FXML
    private void actionBackClientUsage(ActionEvent event) {

        /*Get reference to the Stage the current scene is on
          Note stage variable is inherited from MainSceneController class*/
        stage = (Stage) btnBackClientUsage.getScene().getWindow();

        /*Call SceneController instance method to swap scenes.  
          Note this instance is inherited from MainSceneController class*/
        sceneController.setScene(stage, QueryConsolidatorFXML.getMainFXML());

    }

    /**
     * actionExitClientUsage method: ActionEvent by user to exit program
     */
    @FXML
    private void actionExitClientUsage(ActionEvent event) {

        System.exit(0);

    }

    /**
     * actionSelectClientUsage method: ActionEvent by user selecting to pull
     * data from database based on fields entered (or not entered)
     */
    @FXML
    private void actionSelectClientUsage(ActionEvent event) {

        boolean check = true; //boolean to use throughout method to verify

        lblSelect.setText(""); 

        /*Program allows no entry of dates (wildcards).  If they enter
          a date in either the start or end, check they are valid formats.
        */
        if (!verify.isDate(tfStartDate, tfEndDate)) {

            lblSelect.setText("Invalid date format");
            tfStartDate.clear();
            tfEndDate.clear();
            tfStartDate.requestFocus();
            check = false;

        }

        if (check) { //Verify checks are good

            //Set the global variables
            QueryConsolidatorFXML.setStartDT(tfStartDate.getText());
            QueryConsolidatorFXML.setEndDT(tfEndDate.getText());
            QueryConsolidatorFXML.setClientCode(tfClient.getText());

            /*Call SceneController instance method with 1 argument to lay on top
              of client usage scene on a new stage (Not replace scene onto stage 
              currently there).  Note this instance is inherited from 
              MainSceneController class
            */
            sceneController.setScene(QueryConsolidatorFXML.getClientUsageTableFXML());

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

} //End ClientUsageController class
