package queryconsolidatorfxml;

/** 
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description: 
 * @Reference: 
 *   https://stackoverflow.com/questions/19065464/how-to-populate-a-list-values-to-a-combobox-in-javafx
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Cush
 */
public class QueryConsolidatorFXML extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader theLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        
        Parent root = theLoader.load();
        
        MainScreenController theController = theLoader.getController();
        
        
        Scene scene = new Scene(root);
       
        stage.setTitle("Query Consolidator");
        stage.setScene(scene);
        stage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}