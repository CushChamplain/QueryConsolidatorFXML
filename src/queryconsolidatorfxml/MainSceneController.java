package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description:
 * @Reference:
 * https://stackoverflow.com/questions/19065464/how-to-populate-a-list-values-to-a-combobox-in-javafx
 * https://stackoverflow.com/questions/14370183/passing-parameters-to-a-controller-when-loading-an-fxml
 * http://www.javafxtutorials.com/tutorials/switching-to-different-screens-in-javafx-and-fxml/
 */
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
 *
 * @author Cush
 */
public class MainSceneController implements Initializable {

    Stage stage;
    Verify verify = new Verify(); //Create instance of verify class
    private final static int OPTIONS_BEGIN = 1, OPTIONS_END = 3;
    
    //Create instance of scene controller to load different scene.
    SceneController sceneController = new SceneController();

    //Labels to populate if server not selected and user errors on option select
    @FXML
    private Label lblServer;
    @FXML
    private Label lblSelect;

    @FXML
    private TextField tfSelect;

    @FXML
    private ComboBox comboMain;

    public String comboValueSelected;

    int select;

    

    @FXML
    private void actionExitMain(ActionEvent event) {
        System.out.println("java version: " + System.getProperty("java.version"));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
    }

    @FXML
    private void actionSelectMain(ActionEvent event) {

        boolean check = true;

        //Make sure they selected a server
        if (!verify.isServer(comboMain)) {

            lblServer.setText("Please Select Server");

            check = false;

        }

        //If server is selected check the selection is make
        if (check != false && !verify.isData(tfSelect)) {

            lblServer.setText("");
            lblSelect.setText("Selection blank");
            check = false;
            tfSelect.requestFocus();

        }

        //If server selected and data, check that it's an integer
        if (check != false && !verify.isInt(tfSelect)) {

            lblServer.setText("");
            lblSelect.setText("Must be Integer");
            check = false;
            tfSelect.requestFocus();

        }

        //Check if server selected, data, and is int, check selection is in range
        if (check != false && !verify.isRange(tfSelect, OPTIONS_BEGIN, OPTIONS_END)) {

            lblServer.setText("");
            lblSelect.setText("Invalid Option");
            check = false;
            tfSelect.requestFocus();

        }

        if (check) {

            //Get reference to the Stage the current scene is on (only 1 
            //in this program) by finding the stage comboMain is on
            stage = (Stage) comboMain.getScene().getWindow();

            //Make selection an integer.
            try {
                select = Integer.parseInt(tfSelect.getText());
            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }

            switch (select) {
                case 1:
                    sceneController.setScene(stage, QueryConsolidatorFXML.getClientUsageFXML());
                    break;

                   
                default:
                    System.out.println("Invalid");
                    break;
            }
        }

    }

    public void loadCombo() {

        comboMain.getItems().clear();
        comboMain.getItems().addAll("APPSWPRAC", "APPSWPRAC2");

    }

    @FXML
    public void actionSelectedServer(ActionEvent event) {

        //Set the global static variable to use on later scenes
        QueryConsolidatorFXML.setServer((String) comboMain.getValue());
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCombo(); //Call function to load the ComboBox
    }

}
