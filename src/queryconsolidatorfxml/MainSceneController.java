package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description: This class is the controller for the MainScene.fxml of the U/I
 * The main scene is the first scene that appears. It also holds a Stage,
 * Verify, and SceneController that will be used in subsequent scenes by
 * extending other classes from this class. This is done in order to switch the
 * scenes rather than lay a scene on top of another after user input.
 * @Reference:
 * https://stackoverflow.com/questions/19065464/how-to-populate-a-list-values-to-a-combobox-in-javafx
 * https://stackoverflow.com/questions/14370183/passing-parameters-to-a-controller-when-loading-an-fxml
 * http://www.javafxtutorials.com/tutorials/switching-to-different-screens-in-javafx-and-fxml/
 */
//imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Begin class MainSceneController
 *
 * @author Cush
 */
public class MainSceneController implements Initializable {

    /*Stage variable, Veryify & SceneController instanced used in this class
     And extended classes*/
    Stage stage;
    Verify verify = new Verify();
    SceneController sceneController = new SceneController();

    //User options parameter
    private final static int OPTIONS_BEGIN = 1, OPTIONS_END = 3;

    //Labels to populate if server not selected and/or user errors on option select
    @FXML
    private Label lblServer;
    @FXML
    private Label lblSelect;

    @FXML
    private TextField tfSelect; //Texfield for options selection

    @FXML
    private ComboBox comboMain; //ComboBox for server optoins

    public String comboValueSelected; //String to hold server selected from ComboBox

    int select; //Used in the switch/case below

    /**
     * actionExitMain method: ActionEvent by user to exit program
     */
    @FXML
    private void actionExitMain(ActionEvent event) {
 
        System.exit(0);
        
    }

    /**
     * actionSelectMain method: ActionEvent by user when selecting option
     */
    @FXML
    private void actionSelectMain(ActionEvent event) {

        boolean check = true; //boolean to use throughout method to verify

        //Make sure they selected a server
        if (!verify.isServer(comboMain)) {

            lblServer.setText("Please Select Server");
            check = false;

        }

        //Check option selection is made
        if (check != false && !verify.isData(tfSelect)) {

            lblServer.setText("");
            lblSelect.setText("Selection blank");
            check = false;
            tfSelect.requestFocus();

        }

        //Check option selection is an integer
        if (check != false && !verify.isInt(tfSelect)) {

            lblServer.setText("");
            lblSelect.setText("Must be Integer");
            check = false;
            tfSelect.requestFocus();

        }

        //check optin selection is in range
        if (check != false && !verify.isRange(tfSelect, OPTIONS_BEGIN, OPTIONS_END)) {

            lblServer.setText("");
            lblSelect.setText("Invalid Option");
            check = false;
            tfSelect.requestFocus();

        }

        if (check) { //All entries verified

            /*Get reference to the Stage the current scene is on 
              by finding the stage comboMain is on*/
            stage = (Stage) comboMain.getScene().getWindow();

            //Make selection an integer.
            try {
                select = Integer.parseInt(tfSelect.getText());
            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }

            /*Switch based on selection from user.  Use SceneController class
              to pass the reference to the stage and the scene to load*/
            switch (select) {
                case 1:
                    sceneController.setScene(stage, QueryConsolidatorFXML.getClientUsageFXML());
                    break;

                case 2:
                    sceneController.setScene(stage, QueryConsolidatorFXML.getWKInfoFXML());
                    break;

                case 3:
                    sceneController.setScene(stage, QueryConsolidatorFXML.getUserInfoFXML());
                    break;

                default:
                    System.out.println("Invalid");
                    break;
            }
        }

    }

    /**
     * loadCombo method: Load the ComboBox with servers
     */
    public void loadCombo() {

        comboMain.getItems().clear();
        comboMain.getItems().addAll("APPSWPRAC", "APPSWPRAC2");

    }

    /**
     * actionSelectServer method: ActionEvent by user when selecting server
     */
    @FXML
    public void actionSelectedServer(ActionEvent event) {

        //Set the global static variable to use on later scenes
        QueryConsolidatorFXML.setServer((String) comboMain.getValue());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCombo(); //Call function to load the ComboBox
    }

} //End class MainSceneController
