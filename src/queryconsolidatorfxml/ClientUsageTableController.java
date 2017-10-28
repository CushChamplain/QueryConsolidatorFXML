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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cush
 */
public class ClientUsageTableController implements Initializable {

    @FXML
    private Button btnCloseUsage;
    /*@FXML
    private TableView<?> tblClientUsage;
    @FXML
    private TableColumn<?, ?> colClientCode;
    @FXML
    private TableColumn<?, ?> colUserID;
    @FXML
    private TableColumn<?, ?> colFirstName;
    @FXML
    private TableColumn<?, ?> colLastName;
    @FXML
    private TableColumn<?, ?> colLogin;*/

    @FXML
    private void actionCloseUsageTable (ActionEvent event) {
        
        //Get a reference to the stage so it can be closed
        Stage stageUsageTable = (Stage) btnCloseUsage.getScene().getWindow();
        stageUsageTable.close();
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
