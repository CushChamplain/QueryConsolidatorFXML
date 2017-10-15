package queryconsolidatorfxml;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cush
 */
public class ClientUsageController implements Initializable {
    
    Stage stage; //Create a stage to use later, will be a reference to current stage.
    Parent root;
    public String server;
    
    @FXML
    private Button clientUsageBackBtn;
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void clientUsageBack(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println(getServer());
        
        //Get reference to the Stage the current scene is on (only 1 in this program)
           stage = (Stage) clientUsageBackBtn.getScene().getWindow();
            try {
                //root = FXMLLoader.load(getClass().getResource("ClientUsage.fxml"));
                
                //Load the Client Usage FXML.  Done in two steps so I can get 
                //The FXML loader (theLoader) name in order to get getController()
                //me to 
                FXMLLoader theLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                root = theLoader.load();
                
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void setServer(String serv) {
        
        server = serv;
        
    }
    
    public String getServer() {
        
        return server;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    
}
