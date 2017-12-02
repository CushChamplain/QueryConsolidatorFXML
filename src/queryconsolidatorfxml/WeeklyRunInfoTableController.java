package queryconsolidatorfxml;

/**
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 26, 2017
 * @Description: This class is used to create the table in another stage for the
 * 2. Client Weekly Run Info from the mainScreen.fxml. NOTE: For windows
 * authentication the sqljdbc_auth.dll must be in the jdk<version>/bin folder.
 * The sqljdbc_auth.dll is found in the driver package from MS
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
public class WeeklyRunInfoTableController implements Initializable {

    //Create the url for the connection using windows authentication
    String theURL = "jdbc:sqlserver://localhost;instanceName=" + QueryConsolidatorFXML.getServer()
            + ";integratedSecurity=true;databaseName=QueryConsolidate";

    @FXML
    private Button btnCloseWeekly;

    /*Create the interface ObservableList (type WeeklyRunInfoData) variable and 
      set concrete implementation to FXCollections backed by ArrayList
    */
    private ObservableList<WeeklyRunInfoData> theData = FXCollections.observableArrayList();

    //Declare the TableView and the TableColums variables
    @FXML
    private TableView<WeeklyRunInfoData> tblWeeklyRunInfo;
    @FXML
    private TableColumn<WeeklyRunInfoData, String> colClientCode;
    @FXML
    private TableColumn<WeeklyRunInfoData, String> colClientName;
    @FXML
    private TableColumn<WeeklyRunInfoData, Timestamp> colRunTimestamp;
    @FXML
    private TableColumn<WeeklyRunInfoData, String> colWeek;
    @FXML
    private TableColumn<WeeklyRunInfoData, String> colStatus;

    @FXML
    private void actionCloseWeeklyTable(ActionEvent event) {

        //Get a reference to the stage from a button on the stage so it can be closed
        Stage stageWeeklyTable = (Stage) btnCloseWeekly.getScene().getWindow();
        stageWeeklyTable.close(); //Close the stage

    }

    /**
     * Initializes the controller class. In this case it will execute on the no
     * user input display of the data.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTheCells(); // Call method

        //Use try-with-resource so it will close all after it leaves the try/catch block
        try (Connection con = DriverManager.getConnection(theURL);
                Statement stmt = con.createStatement(); //create the statement off connection

                /*Get the results set by executing the statement with the appropriate
                  Query.  The query is established by what the user inputs with
                  the getQuery method
                */
                ResultSet rs = stmt.executeQuery(getQuery(QueryConsolidatorFXML.getClient()))) {

            while (rs.next()) { //Loop through the ResultSet

                /*Create instances of the WeeklyRunInfoData and add them to the 
                  ObservableList
                */
                theData.add(new WeeklyRunInfoData(rs.getString("clientCode"),
                        rs.getString("clientName"), rs.getTimestamp("runTimestamp"),
                        rs.getString("theWeek"), rs.getString("theStatus")));

            }

        } catch (SQLException sqle) {
            System.out.println("Sql Exception :" + sqle.getMessage());
        }

        //Set the items from ObservableList to table
        tblWeeklyRunInfo.setItems(theData);

        //Clear any global variables for the next run
        QueryConsolidatorFXML.setClientCode("");
    }

    /**
     * setTheCells method: to link the cells for TableColumns with the variables
     * in the WeeklyRunInfoData class.
     */
    private void setTheCells() {

        colClientCode.setCellValueFactory(new PropertyValueFactory<>("clientCode"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        colRunTimestamp.setCellValueFactory(new PropertyValueFactory<>("runTimestamp"));
        colWeek.setCellValueFactory(new PropertyValueFactory<>("theWeek"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("theStatus"));

    }

    /**
     * getQuery method: For getting the appropriate query based user input
     *
     * @return query
     */
    private String getQuery(String client) {
        String query = "";

        query = "select ae.clientCode, oi.clientName, ae.runTimestamp, ae.theWeek, ae.theStatus\n"
                + "from QueryConsolidate.dbo.ANALYTIC_EVENT ae \n"
                + "    inner join QueryConsolidate.dbo.ORG_INFO oi\n"
                + "	   on (ae.clientCode = oi.clientCode)\n"
                + "where ae.clientCode = '" + client + "'\n"
                + "group by ae.clientCode, oi.clientName, ae.runTimestamp, ae.theWeek, ae.theStatus\n"
                + "order by ae.runTimestamp desc";

        return query;
    }

} //End WeeklyRunInfoController method
