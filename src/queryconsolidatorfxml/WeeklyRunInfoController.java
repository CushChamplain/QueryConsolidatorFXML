package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 10, 2017
 * @Description: This class is the controller for the WeeklyRunInfo.fxml of the
 * U/I The user info is the scene that appears when selecting option 2 from the
 * main scene. This class extends MainSceneController class in order to use a
 * Stage variable, Verify & SceneController instances.
 * @Reference:
 */
//Imports
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class WeeklyRunInfoController extends MainSceneController implements Initializable {

    //Create the url for the connection using windows authentication
    String theURL = "jdbc:sqlserver://localhost;instanceName=" + QueryConsolidatorFXML.getServer()
            + ";integratedSecurity=true;databaseName=QueryConsolidate";

    @FXML
    private Button btnBackWeeklyInfo; //Used to get reference to stage and that's it.

    //Declare the FXML fields
    @FXML
    Label lblSelInRun;
    @FXML
    Label lblSelRunHist;
    @FXML
    private TextField tfClientInRun;
    @FXML
    private TextField tfClientRunHist;
    @FXML
    private TextArea taNote;

    /**
     * actionBackWeeklyInfo method: ActionEvent by user to go back to main
     */
    @FXML
    private void actionBackWeeklyInfo(ActionEvent event) {

        /*Get reference to the Stage the current scene is on
          Note stage variable is inherited from MainSceneController class*/
        stage = (Stage) btnBackWeeklyInfo.getScene().getWindow();

        /*Call SceneController instance method to swap scenes.  
          Note this instance is inherited from MainSceneController class.*/
        sceneController.setScene(stage, QueryConsolidatorFXML.getMainFXML());

    }

    /**
     * actionExitWeeklyInfo method: ActionEvent by user to exit program
     */
    @FXML
    private void actionExitWeeklyInfo(ActionEvent event) {

        System.exit(0);

    }

    /**
     * actionSelectInRun method: ActionEvent by user selecting to pull data from
     * database based on fields entered
     */
    @FXML
    private void actionSelectInRun(ActionEvent event) {

        boolean check = true;

        //Clear the labels to reset
        lblSelRunHist.setText("");
        lblSelInRun.setText("");

        tfClientRunHist.clear(); //Clear TextField not relevant to action

        //Check they entered a client code in TextField
        if (!verify.isData(tfClientInRun)) {

            lblSelInRun.setText("Enter code");
            tfClientInRun.requestFocus();
            check = false;

        }

        //Check the client is a valid client
        if (check && !verify.isClient(tfClientInRun)) {

            lblSelInRun.setText("Invalid code");
            tfClientInRun.requestFocus();
            check = false;

        }

        if (check) { //If no user input issues

            //Use try-with-resource so it will close all after it leaves the try/catch block
            try (Connection con = DriverManager.getConnection(theURL);
                    Statement stmt = con.createStatement(); //create the statement off connection

                    //Get restuls.
                    ResultSet rs = stmt.executeQuery("select weeklyrun \n"
                            + "from QueryConsolidate.dbo.ORG_INFO\n"
                            + "where clientCode = '" + tfClientInRun.getText() + "'")) {

                rs.next(); //Move to the first item in results

                /*If the weeklyRun column is a 1 (data structure considers in run)
                  they are in the run
                 */
                if (rs.getInt("weeklyRun") == 1) {

                    lblSelInRun.setText("Yes");
                    tfClientInRun.requestFocus(); //get focus back for next entry

                } else { //weeklyRun column not a 1 indicating not in run
                    lblSelInRun.setText("No");
                    tfClientInRun.requestFocus(); //get focus back for next entry
                }

            } catch (SQLException sqle) {
                System.out.println("Sql Exception :" + sqle.getMessage());
            }

        }

    }

    /**
     * actionSelectRunHist method: ActionEvent by user selecting to pull data
     * from database based on fields entered
     */
    @FXML
    private void actionSelectRunHist(ActionEvent event) {

        boolean check = true;

        //Clear the labels to reset
        lblSelRunHist.setText("");
        lblSelInRun.setText("");

        tfClientInRun.clear(); //Clear TextField not relevant to action

        //Check they entered a client code in TextField
        if (!verify.isData(tfClientRunHist)) {

            lblSelRunHist.setText("Enter code");
            tfClientRunHist.requestFocus();
            check = false;

        }

        //Check the client is a valid client
        if (check && !verify.isClient(tfClientRunHist)) {

            lblSelRunHist.setText("Invalid code");
            tfClientRunHist.requestFocus();
            check = false;

        }

        if (check) { //client code is entered and valid

            /*Get focus for after display so user can see what they entered
              last and just start typing to enter a different code
            */
            tfClientRunHist.requestFocus();

            //Set the global client code variable
            QueryConsolidatorFXML.setClientCode(tfClientRunHist.getText());

            /*Call SceneController instance method with 1 argument to lay on top
              of weekly run info screen (Not take the stage currently there).
              Note this instance is inherited from MainSceneController class.
            */
            sceneController.setScene(QueryConsolidatorFXML.getWeeklyRunInfoTableFXML());

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
