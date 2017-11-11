package queryconsolidatorfxml;

import java.sql.Timestamp;

/**
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 27, 2017
 * @Subclass ClientUsageData Description: This class is used to set a class type
 * and data for the TableView of the ClientUsageTableController.
 * Reference:
 * https://www.youtube.com/watch?v=3tmz-0g3EPs
 */
//Imports
//Begin Subclass ClientUsageData
public class ClientUsageData {

    private String clientCode;
    private String userID;
    private String firstName;
    private String lastName;
    private Timestamp loginTimestamp;

    public ClientUsageData(String clientCode, String userID, String firstName,
            String lastName, Timestamp loginTimestamp) {
        this.clientCode = clientCode;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginTimestamp = loginTimestamp;
    }

    ClientUsageData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the clientCode
     */
    public String getClientCode() {
        return clientCode;
    }

    /**
     * @param clientCode the clientCode to set
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the loginTimestamp
     */
    public Timestamp getLoginTimestamp() {
        return loginTimestamp;
    }

    /**
     * @param loginTimestamp the loginTimestamp to set
     */
    public void setLoginTimestamp(Timestamp loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

} //End Subclass ClientUsageData
