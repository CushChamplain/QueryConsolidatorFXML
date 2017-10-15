package queryconsolidatorfxml;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** 
 * @Course: SDEV 435 ~ Applied Software Practice I
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Oct 5, 2017
 * @Subclass Verify Description:
 * @Reference:
 */
//Imports

//Begin Subclass Verify
public class Verify {
    
    /**
     * Constructor 
     */
    public Verify() {
       
    }

    public boolean isServer (ComboBox combo) {
        
        boolean boolChecks = true;
        if (combo.getSelectionModel().isEmpty()) {
            
            boolChecks = false;
        }
        
        return boolChecks;
        
    }
    
    public boolean isInt (TextField tf) {
        boolean boolChecks = true;
        
        //Check if it's an integer by trying to parse the TextField to int
        try {
            int i = Integer.parseInt(tf.getText());
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            boolChecks = false;
        }
        
        return boolChecks;
        
    }
    
    public boolean isData(TextField tf) {

        boolean boolChecks = true;

        if (tf.getText().isEmpty()) {
            
            boolChecks = false;

        }

        return boolChecks;
        
    }
    
     public boolean isRange(TextField tf, int begin, int end) {
         
         boolean boolChecks = true;
         
         try {
            int i = Integer.parseInt(tf.getText());
            
            if (i < begin || i > end) {
                
                boolChecks = false;
                
            }
            
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
            boolChecks = false;
        }
         
        return boolChecks;
     }

   
} //End Subclass Verify