package queryconsolidatorfxml;

/**
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Nov 11, 2017
 * @Subclass UserInfoData Description: This class is used to set a class type
 * and data for the TableView of the UserInfoTableController.
 * Reference:
 * https://www.youtube.com/watch?v=3tmz-0g3EPs
 */

//Begin Subclass UserInfoData
public class UserInfoData {

    //Declare variables
    private String userID;
    private String clientCode;
    private String firstName;
    private String lastName;
    private String userStatus;

    /**
     * Constructor with parameters
     * @param userID
     * @param clientCode
     * @param firstName
     * @param lastName
     * @param userStatus
     */
    public UserInfoData(String userID, String clientCode, String firstName, 
            String lastName, String userStatus) {
        this.userID = userID;
        this.clientCode = clientCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userStatus = userStatus;
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
     * @return the userStatus
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

} //End Subclass UserInfoData
