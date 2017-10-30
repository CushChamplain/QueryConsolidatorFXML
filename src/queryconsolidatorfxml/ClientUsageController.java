package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description:
 * @Reference:
 * https://forums.asp.net/t/1945240.aspx?regular+expression+to+check+date+mm+dd+yyyy+which+allows+1+1+2013
 * http://ruby.fgcu.edu/courses/mpenderg/gettingstartedwithnetbeans/SQLSERVERandNetbeans.html
 */
//import java.sql.Connection;
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
 * FXML Controller class
 *
 * @author Cush
 */
public class ClientUsageController extends MainSceneController implements Initializable {



    @FXML
    private Button btnBackClientUsage; //Used to get reference to stage and that's it.

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
     * Initializes the controller class.
     */
    @FXML
    private void actionBackClientUsage(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println(QueryConsolidatorFXML.getServer());

        //Get reference to the Stage the current scene is on
        //Note stage variable is inherited from MainSceneController class
        stage = (Stage) btnBackClientUsage.getScene().getWindow();

        //Call SceneController instance method to swap scenes.  
        //Note this instance is inherited from MainSceneController class.
        sceneController.setScene(stage, QueryConsolidatorFXML.getMainFXML());

    }

    @FXML
    private void actionExitClientUsage(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    private void actionSelectClientUsage(ActionEvent event) {

        boolean check = true;

        lblSelect.setText("");

        //Program allows none entry of dates (wildcards).  If they enter
        //a date in either the start or end, check they are valid formats.
        if (!verify.isDate(tfStartDate, tfEndDate)) {

            lblSelect.setText("Invalid date format");
            tfStartDate.clear();
            tfEndDate.clear();
            tfStartDate.requestFocus();
            check = false;

        }

        if (check) { //Validation checks are good

            //Set the global variables
            QueryConsolidatorFXML.setStartDT(tfStartDate.getText());
            QueryConsolidatorFXML.setEndDT(tfEndDate.getText());
            QueryConsolidatorFXML.setClientCode(tfClient.getText());

            //Call SceneController instance method with 1 argument to lay on top
            //of client usage screen (Not take the stage currently there).
            //Note this instance is inherited from MainSceneController class.
            sceneController.setScene(QueryConsolidatorFXML.getClientUsageTableFXML());

            
        }

    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
