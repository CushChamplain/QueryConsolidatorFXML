package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Nov 11, 2017
 * @Description: This class is used to create the table in another stage for the
 * 3. User Info from the mainScreen.fxml. NOTE: For windows authentication the
 * sqljdbc_auth.dll must be in the jdk<version>/bin folder. The sqljdbc_auth.dll
 * is found in the driver package from MS
 * (https://www.microsoft.com/en-us/download/details.aspx?displaylang=en&id=11774).
 * @Reference:
 * https://querysurge.zendesk.com/hc/en-us/articles/205766836-Setup-for-SQL-Server-Windows-Authentication
 * https://docs.oracle.com/javafx/2/collections/jfxpub-collections.htm
 * http://ruby.fgcu.edu/courses/mpenderg/gettingstartedwithnetbeans/SQLSERVERandNetbeans.html
 */
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cush
 */
public class UserInfoTableController implements Initializable {

    //Create the url for the connection using windows authentication
    String theURL = "jdbc:sqlserver://localhost;instanceName=" + QueryConsolidatorFXML.getServer()
            + ";integratedSecurity=true;databaseName=QueryConsolidate";
    
    String client = QueryConsolidatorFXML.getClient();
    String status = QueryConsolidatorFXML.getUserStatus();

    @FXML
    private Button btnCloseUser;

    //Create the interface ObservableList (type WeeklyRunInfoData) variable and 
    //set concrete implementation to FXCollections backed by ArrayList
    private ObservableList<UserInfoData> theData = FXCollections.observableArrayList();

    //Declare the TableView and the TableColums variables
    @FXML
    private TableView<UserInfoData> tblUserInfo;
    @FXML
    private TableColumn<UserInfoData, String> colUserID;
    @FXML
    private TableColumn<UserInfoData, String> colClientCode;
    @FXML
    private TableColumn<UserInfoData, String> colFirstName;
    @FXML
    private TableColumn<UserInfoData, String> colLastName;
    @FXML
    private TableColumn<UserInfoData, String> colUserStatus;

    @FXML
    private void actionCloseUserTable(ActionEvent event) {

        //Get a reference to the stage from a button on the stage so it can be closed
        Stage stageWeeklyTable = (Stage) btnCloseUser.getScene().getWindow();
        stageWeeklyTable.close(); //Close the stage

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTheCells();

        //Use try-with-resource so it will close all after it leaves the try/catch block
        try (Connection con = DriverManager.getConnection(theURL);
                Statement stmt = con.createStatement(); //create the statement off connection

                //Get the results set by executing the statement with the appropriate
                //Query.  The query is established by what the user inputs with
                //the getQuery method.
                ResultSet rs = stmt.executeQuery(getQuery(client, status))) {

            while (rs.next()) { //Loop through the ResultSet

                //Create instances of the WeeklyRunInfoData and add them to the 
                //ObservableList
                theData.add(new UserInfoData(rs.getString("userID"),
                        rs.getString("clientCode"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("userStatus")));

            }

            System.out.println("Connected to database !");

        } catch (SQLException sqle) {
            System.out.println("Sql Exception :" + sqle.getMessage());
        }

        //Set the items from ObservableList to table
        tblUserInfo.setItems(theData);

        //Clear any global variables for the next run
        QueryConsolidatorFXML.setClientCode("");

    }

    /**
     * Method to link the cells for TableColumns with the variables in the
     * UserInfoData class.
     */
    private void setTheCells() {

        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colClientCode.setCellValueFactory(new PropertyValueFactory<>("clientCode"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colUserStatus.setCellValueFactory(new PropertyValueFactory<>("userStatus"));

    }

    private String getQuery(String theClient, String userStatus) {
        String query = "";

        if (theClient.isEmpty()) { //If no client code entered (this screen allows)

            query = "select userID, clientCode, firstName, lastName, userStatus\n"
                    + "from QueryConsolidate.dbo.USER_INFO \n"
                    + "where userStatus = '" + userStatus + "\n"
                    + "group by userID, clientCode, firstName, lastName, userStatus\n"
                    + "order by clientCode, lastName";

        } else { //Client code entered

            query = "select userID, clientCode, firstName, lastName, userStatus\n"
                    + "from QueryConsolidate.dbo.USER_INFO \n"
                    + "where userStatus = '" + userStatus + "and clientCode = '"
                    + theClient + "\n"
                    + "group by userID, clientCode, firstName, lastName, userStatus\n"
                    + "order by clientCode, lastName";

        }
        
        return query;

    }

}
