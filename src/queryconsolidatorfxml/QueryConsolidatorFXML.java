package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Description:
 * @Reference: This is the starting class of the application.  It holds the 
 * start function which loads the mainScene.fxml U/I scene.  It also holds
 * global static variables needed throughout the program with their set/get
 * methods
 * https://stackoverflow.com/questions/19065464/how-to-populate-a-list-values-to-a-combobox-in-javafx
 */

//imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Begin class QueryConsolidatorFXML
 * @author Cush
 */
public class QueryConsolidatorFXML extends Application {

    //Setup global final variables for different scene fxml loads
    private static final String MAIN_SCENE = "MainScene.fxml";
    private static final String CLIENT_USAGE = "ClientUsage.fxml";
    private static final String CLIENT_USAGE_TABLE = "ClientUsageTable.fxml";
    private static final String CLIENT_WEEKLY= "WeeklyRunInfo.fxml";
    private static final String CLIENT_WEEKLY_TABLE = "WeeklyRunInfoTable.fxml";
    private static final String USER_INFO = "UserInfo.fxml";
    private static final String USER_INFO_TABLE = "UserInfoTable.fxml";
    
    //Set a global variable for set and get functions used throughout program
    private static String server;
    private static String startDT;
    private static String endDT;
    private static String client;
    private static String userStatus;

    /**
     * start method: Starts the application
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {

        //Create the FXMLLoader
        FXMLLoader theLoader = new FXMLLoader(getClass().getResource(getMainFXML()));

        Parent root = theLoader.load(); //Call load method and assign to root

        Scene scene = new Scene(root);  //Create the Scene and add FXML to it

        stage.setTitle("Query Consolidator"); //Set the title
        stage.setScene(scene); //Set the scene to the stage
        stage.show(); //Show the stage

    } 

    /**
     * main method: Load arguments from command line.  Not needed
     * in program but leaving in for future development
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    } 

    /**
     * setServer method: Set the server based from user input
     * @param serv
     */
    public static void setServer (String serv) {
        server = serv;
    }
    
    /**
     * setStartDT method: Set the start date from user input
     * @param start
     */
    public static void setStartDT(String start) {
        startDT = start;
    }

    /**
     * setEndDT method: Set the end date from user input
     * @param end
     */
    public static void setEndDT(String end) {
        endDT = end;
    }

    /**
     * setClientCode method: Set the client code from user input
     * @param theClient
     */
    public static void setClientCode(String theClient) {
        client = theClient;
    }
    
    /**
     * seUserStatus method: Set the user status from user input
     * @param UStatus
     */
    public static void setUserStatus(String UStatus) {
        userStatus = UStatus;
    }
    
     /**
     * getServer method: Return the server set by user
     * @return server
     */
    public static String getServer () {
        return server;
    }
    
    /**
     * getStartDT method: Return the start date set by user
     * @return startDT
     */
    public static String getStartDT() {
        return startDT;
    }

    /**
     * getEndDT method: Return the end date set by user
     * @return endDT
     */
    public static String getEndDT() {
        return endDT;
    }

    /**
     * getClient method: Return the client set by user
     * @return client
     */
    public static String getClient() {
        return client;
    }
    
    /**
     * getUserStatus method: Return the user status set by user
     * @return userStatus
     */
    public static String getUserStatus() {
        return userStatus;
    }
    
    /**
     * getMainFXML method: Return a string of main scene FXML file
     * @return MAIN_SCENE
     */
    public static String getMainFXML() {
        return MAIN_SCENE;
    } 
    
    /**
     * getClientUsageFXML method: Return a string of client usage FXML file
     * @return CLIENT_USAGE
     */
    public static String getClientUsageFXML() {
        return CLIENT_USAGE;
    }
    
    /**
     * getWKInfoFXML method: Return a string of weekly info FXML file
     * @return CLIENT_WEEKLY
     */
    public static String getWKInfoFXML() {
        return CLIENT_WEEKLY;
    }
    
    /**
     * getUserInfoFXML method: Return a string of user info FXML file
     * @return getUserInfoFXML
     */
    public static String getUserInfoFXML() {
        return USER_INFO;
    }
    
    /**
     * getUserInfoTableFXML method: Return a string of user info table FXML file
     * @return USER_INFO_TABLE
     */
    public static String getUserInfoTableFXML() {
        return USER_INFO_TABLE;
    }
    
    /**
     * getClientUSageTableFXML method: Return a string of client usage table
     * FXML file
     * @return CLIENT_USAGE_TABLE
     */
    public static String getClientUsageTableFXML() {
        return CLIENT_USAGE_TABLE;
    }
    
    /**
     * getWeeklyRunInfoTableFXML method: Return a string of weekly run info 
     * FXML file
     * @return CLIENT_WEEKLY_TABLE
     */
    public static String getWeeklyRunInfoTableFXML() {
        return CLIENT_WEEKLY_TABLE;
    }
    
} //End class QueryConsolidatorFXML
