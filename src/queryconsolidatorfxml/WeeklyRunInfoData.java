package queryconsolidatorfxml;

import java.sql.Timestamp;

/** 
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Nov 7, 2017
 * @Subclass WeeklyInfoData Description: This class is used to set a class type
 * and data for the TableView of the WeeklyRunInfoTableController.
 * Reference:
 * https://www.youtube.com/watch?v=3tmz-0g3EPs
 */
 
//Begin Subclass WeeklyRunInfoData
public class WeeklyRunInfoData {
    
    private String clientCode;
    private String clientName;
    private Timestamp runTimestamp;
    private String theWeek;
    private String theStatus;

    public WeeklyRunInfoData(String clientCode, String clientName, 
            Timestamp runTimestamp, String theWeek, String theStatus) {
        this.clientCode = clientCode;
        this.clientName = clientName;
        this.runTimestamp = runTimestamp;
        this.theWeek = theWeek;
        this.theStatus = theStatus;
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
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the runTimestamp
     */
    public Timestamp getRunTimestamp() {
        return runTimestamp;
    }

    /**
     * @param runTimestamp the runTimestamp to set
     */
    public void setRunTimestamp(Timestamp runTimestamp) {
        this.runTimestamp = runTimestamp;
    }

    /**
     * @return the theWeek
     */
    public String getTheWeek() {
        return theWeek;
    }

    /**
     * @param theWeek the theWeek to set
     */
    public void setTheWeek(String theWeek) {
        this.theWeek = theWeek;
    }

    /**
     * @return the theStatus
     */
    public String getTheStatus() {
        return theStatus;
    }

    /**
     * @param theStatus the theStatus to set
     */
    public void setTheStatus(String theStatus) {
        this.theStatus = theStatus;
    }

} //End Subclass WeeklyRunInfoData