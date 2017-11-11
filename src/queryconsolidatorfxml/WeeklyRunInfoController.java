package queryconsolidatorfxml;

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
 * FXML Controller class
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
     * Initializes the controller class.
     */
    @FXML
    private void actionBackWeeklyInfo(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println(QueryConsolidatorFXML.getServer());

        //Get reference to the Stage the current scene is on
        //Note stage variable is inherited from MainSceneController class
        stage = (Stage) btnBackWeeklyInfo.getScene().getWindow();

        //Call SceneController instance method to swap scenes.  
        //Note this instance is inherited from MainSceneController class.
        sceneController.setScene(stage, QueryConsolidatorFXML.getMainFXML());

    }

    @FXML
    private void actionExitWeeklyInfo(ActionEvent event) {

        System.exit(0);

    }

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

        if (check) {

            //Use try-with-resource so it will close all after it leaves the try/catch block
            try (Connection con = DriverManager.getConnection(theURL);
                    Statement stmt = con.createStatement(); //create the statement off connection

                    //Get restuls.
                    ResultSet rs = stmt.executeQuery("select weeklyrun \n"
                            + "from QueryConsolidate.dbo.ORG_INFO\n"
                            + "where clientCode = '" + tfClientInRun.getText() + "'")) {

                if (!rs.next()) { //If nothing in ResultSet

                    lblSelInRun.setText("Invalid code");
                    tfClientInRun.requestFocus();

                } else if (rs.getInt("weeklyRun") == 1) {

                    lblSelInRun.setText("Yes");
                    tfClientInRun.requestFocus();

                } else {
                    lblSelInRun.setText("No");
                    tfClientInRun.requestFocus();
                }

                System.out.println("Connected to database !");

            } catch (SQLException sqle) {
                System.out.println("Sql Exception :" + sqle.getMessage());
            }

        }

    }

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

        if (check) { //client code is entered

            tfClientRunHist.requestFocus();
            //Set the global client code variable
            QueryConsolidatorFXML.setClientCode(tfClientRunHist.getText());

            //Call SceneController instance method with 1 argument to lay on top
            //of weekly run info screen (Not take the stage currently there).
            //Note this instance is inherited from MainSceneController class.
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
