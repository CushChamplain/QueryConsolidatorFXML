
package queryconsolidatorfxml;

/** 
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description: 
 * @Reference: 
 *   https://stackoverflow.com/questions/19065464/how-to-populate-a-list-values-to-a-combobox-in-javafx
 *   https://stackoverflow.com/questions/14370183/passing-parameters-to-a-controller-when-loading-an-fxml
 *   http://www.javafxtutorials.com/tutorials/switching-to-different-screens-in-javafx-and-fxml/
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author Cush
 */
public class MainScreenController implements Initializable {
    
    Stage stage; //Create a stage to use later, will be a reference to current stage.
    Parent root;
    
    @FXML
    private Button exitMainBtn;
    @FXML
    private Button selectMainBtn;
    
    
    @FXML
    private ComboBox comboMain;
    
    
    
    public String comboValueSelected;
    
    
    @FXML
    private void exitMain(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println("java version: "+System.getProperty("java.version"));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
    }
    
    @FXML
    private void selectMain(ActionEvent event) {
        
        if ("Server1".equals(comboValueSelected)) {
            
           //Get reference to the Stage the current scene is on (only 1 in this program)
           stage = (Stage) selectMainBtn.getScene().getWindow();
            try {
                //root = FXMLLoader.load(getClass().getResource("ClientUsage.fxml"));
                
                //Load the Client Usage FXML.  Done in two steps so I can get 
                //The FXML loader (theLoader) name in order to get getController()
                //me to 
                FXMLLoader theLoader = new FXMLLoader(getClass().getResource("ClientUsage.fxml"));
                root = theLoader.load();
                
                //Get the controller and pass the server name to function in ClientUsageController
                //In order to poplate variable there.  
                ClientUsageController usageController = theLoader.<ClientUsageController>getController();
                usageController.setServer(comboValueSelected);
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();
        //System.out.println(comboValueSelected);
        
    }
    
   
     
    
    public void loadCombo() {
        
        comboMain.getItems().clear();
        comboMain.getItems().addAll("Server1", "Server2");
       
        
    }
    
    @FXML
    public void selectedServer(ActionEvent event) {
        
        comboValueSelected = (String) comboMain.getValue();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCombo(); //Call function to load the ComboBox
    }    
    
}
