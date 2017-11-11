package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 26, 2017
 * @Description: This class is used to create the table in another stage for the
 * 1. Client usage option from the mainScreen.fxml. NOTE: For windows
 * authentication the sqljdbc_auth.dll must be in the jdk<version>/bin folder.
 * The sqljdbc_auth.dll is found in the driver package from MS
 * (https://www.microsoft.com/en-us/download/details.aspx?displaylang=en&id=11774).
 * @Reference:
 * https://querysurge.zendesk.com/hc/en-us/articles/205766836-Setup-for-SQL-Server-Windows-Authentication
 * https://docs.oracle.com/javafx/2/collections/jfxpub-collections.htm
 */
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class ClientUsageTableController implements Initializable {

    //Create the url for the connection using windows authentication
    String theURL = "jdbc:sqlserver://localhost;instanceName=" + QueryConsolidatorFXML.getServer()
            + ";integratedSecurity=true;databaseName=QueryConsolidate";

    @FXML
    private Button btnCloseUsage;

    //Create the interface ObservableList (type ClientUsageData) variable and 
    //set concrete implementation to FXCollections backed by ArrayList
    private ObservableList<ClientUsageData> theData = FXCollections.observableArrayList();

    //Declare the TableView and the TableColums variables
    @FXML
    private TableView<ClientUsageData> tblClientUsage;
    @FXML
    private TableColumn<ClientUsageData, String> colClientCode;
    @FXML
    private TableColumn<ClientUsageData, String> colUserID;
    @FXML
    private TableColumn<ClientUsageData, String> colFirstName;
    @FXML
    private TableColumn<ClientUsageData, String> colLastName;
    @FXML
    private TableColumn<ClientUsageData, Timestamp> colLogin;

    @FXML
    private void actionCloseUsageTable(ActionEvent event) {

        //Get a reference to the stage from a button on the stage so it can be closed
        Stage stageUsageTable = (Stage) btnCloseUsage.getScene().getWindow();
        stageUsageTable.close(); //Close the stage

    }

    /**
     * Initializes the controller class. In this case it will execute on the no
     * user input display of the data.
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
                ResultSet rs = stmt.executeQuery(getQuery(QueryConsolidatorFXML.getStartDT(),
                        QueryConsolidatorFXML.getEndDT(), QueryConsolidatorFXML.getClient()))) {

            while (rs.next()) { //Loop through the ResultSet

                //Create instances of the ClientUsageData and add them to the 
                //ObservableList
                theData.add(new ClientUsageData(rs.getString("clientCode"),
                        rs.getString("userID"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getTimestamp("loginTimestamp")));

            }

            System.out.println("Connected to database !");

        } catch (SQLException sqle) {
            System.out.println("Sql Exception :" + sqle.getMessage());
        }

        //Set the items from ObservableList to table
        tblClientUsage.setItems(theData);

        //Clear any global variables for the next run
        QueryConsolidatorFXML.setStartDT("");
        QueryConsolidatorFXML.setEndDT("");
        QueryConsolidatorFXML.setClientCode("");

    }

    /**
     * Method to link the cells for TableColumns with the variables in the
     * ClientUsageData class.
     */
    private void setTheCells() {

        colClientCode.setCellValueFactory(new PropertyValueFactory<>("clientCode"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("loginTimestamp"));

    }

    private String getQuery(String start, String end, String client) {
        String query = "";

        //No user input
        if (start.isEmpty() && end.isEmpty() && client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        } //User input only client code
        else if (start.isEmpty() && end.isEmpty() && !client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "where ul.clientCode = '" + client + "'\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        } //User input end date and client
        else if (start.isEmpty() && !end.isEmpty() && !client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "where ul.loginTimestamp < '" + end + "'\n"
                    + "     and ul.clientCode = '" + client + "'\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        } //User input only start date
        else if (!start.isEmpty() && end.isEmpty() && client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "where ul.loginTimestamp >= '" + start + "'\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        } //User input start date and end date
        else if (!start.isEmpty() && !end.isEmpty() && client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "where ul.loginTimestamp >= '" + start + "'\n"
                    + "    and ul.loginTimestamp < '" + end + "'\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        } //User input start, end, and client code
        else if (!start.isEmpty() && !end.isEmpty() && !client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "where ul.loginTimestamp >= '" + start + "'\n"
                    + "    and ul.loginTimestamp < '" + end + "'\n"
                    + "    and ul.clientCode = '" + client + "'\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        } //User input start and client code
        else if (!start.isEmpty() && end.isEmpty() && !client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "where ul.loginTimestamp >= '" + start + "'\n"
                    + "    and ul.clientCode = '" + client + "'\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        } //User input just end date
        else if (start.isEmpty() && !end.isEmpty() && client.isEmpty()) {

            query = "select ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "from QueryConsolidate.dbo.USERMGMT_LOG ul \n"
                    + "    inner join QueryConsolidate.dbo.USER_INFO ui\n"
                    + "	   on (ul.userID = ui.userID)\n"
                    + "where ul.loginTimestamp < '" + end + "'\n"
                    + "group by ul.clientCode, ul.userID, ui.firstName, ui.lastName, ul.loginTimestamp\n"
                    + "order by ul.loginTimestamp desc;";

        }

        return query;
    }

}
