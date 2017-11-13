/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queryconsolidatorfxml;

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
     * Initializes the controller class.
     */
    @FXML
    private void actionBackUserInfo(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println(QueryConsolidatorFXML.getServer());

        //Get reference to the Stage the current scene is on
        //Note stage variable is inherited from MainSceneController class
        stage = (Stage) btnBackUserInfo.getScene().getWindow();

        //Call SceneController instance method to swap scenes.  
        //Note this instance is inherited from MainSceneController class.
        sceneController.setScene(stage, QueryConsolidatorFXML.getMainFXML());

    }

    @FXML
    private void actionExitUserInfo(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    private void actionSelectActive(ActionEvent event) {

        boolean check = true;

        //Clear the labels to reset
        lblActive.setText("");
        lblInactive.setText("");

        tfInactive.clear(); //Clear TextField not relevant to action
        if (!tfActive.getText().isEmpty() && !verify.isClient(tfActive)) {

            lblActive.setText("Invalid code");
            tfActive.requestFocus();
            check = false;

        }

        if (check) {

            //Get focus for after display so user can see what they entered
            //last and just start typing to enter a different code
            tfActive.requestFocus();

            //Set the global client code variable
            QueryConsolidatorFXML.setClientCode(tfActive.getText());
            
            //Set the gobal user status variable, will be used for queries
            QueryConsolidatorFXML.setUserStatus("active");

            //Call SceneController instance method with 1 argument to lay on top
            //of weekly run info screen (Not take the stage currently there).
            //Note this instance is inherited from MainSceneController class.
            sceneController.setScene(QueryConsolidatorFXML.getUserInfoTableFXML());

        }

    }

    @FXML
    private void actionSelectInactive(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
