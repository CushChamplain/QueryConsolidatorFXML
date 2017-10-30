package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description:
 * @Reference:
 * https://stackoverflow.com/questions/19065464/how-to-populate-a-list-values-to-a-combobox-in-javafx
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

    //Setup global final variables for different screen fxml loads
    private static final String MAIN_SCREEN = "MainScreen.fxml";
    private static final String CLIENT_USAGE = "ClientUsage.fxml";
    private static final String CLIENT_USAGE_TABLE = "ClientUsageTable.fxml";
    private static final String CLIENT_WEEKLY_INFO = "ClientWeelyInfo.fxml";
    private static final String USER_INFO = "UserInfo.fxml";
    
    //Set a global variable for set and get functions used throughout program
    private static String server;
    private static String startDT;
    private static String endDT;
    private static String client;
   
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader theLoader = new FXMLLoader(getClass().getResource(getMainFXML()));

        Parent root = theLoader.load();

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

    public static String getMainFXML() {

        return MAIN_SCREEN;

    }
    public static String getClientUsageFXML() {

        return CLIENT_USAGE;

    }
    
    public static String getWKInfoFXML() {

        return CLIENT_WEEKLY_INFO;

    }
    
    public static String getUserInfoFXML() {

        return USER_INFO;

    }
    
    public static String getClientUsageTableFXML() {
        return CLIENT_USAGE_TABLE;
    }
    
    public static void setServer (String serv) {
        
        server = serv;
        
    }
    
    public static String getServer () {
        
        return server;
        
    }
    
    public static void setStartDT(String startDT) {
        QueryConsolidatorFXML.startDT = startDT;
    }

    public static void setEndDT(String endDT) {
        QueryConsolidatorFXML.endDT = endDT;
    }

    public static void setClientCode(String client) {
        QueryConsolidatorFXML.client = client;
    }
    
    public static String getStartDT() {
        return startDT;
    }

    public static String getEndDT() {
        return endDT;
    }

    public static String getClient() {
        return client;
    }
    

}
