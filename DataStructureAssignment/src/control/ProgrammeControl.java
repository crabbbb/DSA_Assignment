package control;

/**
 * @author LOH XIN JIE
 */
import adt.DoublyLinkedList;
import dao.*;
import entity.*;
import java.io.IOException;
import utility.*;

public class ProgrammeControl {

    private DBTable db;
    private ProgrammeTutorialGrpControl ptgControl;

    public ProgrammeControl() {
        db = new DBTable();
        ptgControl = new ProgrammeTutorialGrpControl();
    }

    public DoublyLinkedList<Programme> getAllData() {
        try {
            return db.Programme.getAll();
        } catch (IOException | ClassNotFoundException ex) {
            Message.ErrorMessage(ex.getMessage());
        }
        return null;
    }

    public Object[] convertToObject() {
        return null;
    }

    public boolean saveIntoFile(Programme p, int numberOfTutGrp) {
        try {
            if (db.Programme.Add(p)) {
                // successfull add Programme
                ptgControl.addTutorialGrp(numberOfTutGrp, p.getProgrammeID());

                return true;
            }

        } catch (IOException | ClassNotFoundException ex) {
            Message.ErrorMessage(ex.getMessage());
        }

        return false;
    }

    // programmeID cannot have special character, cannot be empty, must in 3 character
    public String programmeIdValidate(String programmeID) {
        if (!programmeID.isEmpty()) {
            // check special character
            char[] array = programmeID.toCharArray();
            if (array.length == 3) {
                for (int i = 0; i < array.length; i++) {
                    if (!Character.isLetter(array[i])) {
                        // programme ID have one is not letter then can stop the loop and return null, because not fulfill requirement
                        Message.ErrorMessage("Programme Code cannot have special character");
                        return null;
                    } else if (i == 2 && Character.isLetter(array[i])) {
                        // i == 2 means already last && array[i] is letter == all requirement match return value
                        return programmeID;
                    }
                }
            } else {
                Message.ErrorMessage("Programme Code must in 3 character");
            }
        } else {
            Message.ErrorMessage("Programme Code cannot be empty");
        }
        return null;
    }

    // programmeName cannot be empty
    public String programmeNameValidate(String programmeName) {
        if (!programmeName.isEmpty()) {
            return programmeName;
        }

        Message.ErrorMessage("Programme Name cannot be empty");
        return null;
    }

    // cannot be negative value, cannot be alpha, cannot be empty
    public int targetCapacityValidate(String targetCapacity) {
        // check empty
        if (!targetCapacity.isEmpty()) {
            // check number format
            try {
                int target = Converter.convertStringToInteger(targetCapacity);

                // check negative value
                if (target > 0) {
                    return target;
                } else {
                    // smaller and equals to 0
                    Message.ErrorMessage("Target Quantity Of Student cannot in negative value ( eg : 300 )");
                }
            } catch (NumberFormatException ex) {
                Message.ErrorMessage("Invalid format error\nTarget Quantity Of Student must in NUMERIC FROMAT! ( eg : 300 )");
            }
        } else {
            Message.ErrorMessage("Target Quantity Of Student cannot be empty");
        }
        return -1;
    }

    // cannot be negative value, cannot be alpha, cannot be empty
    public int numberOfTutGrpValidate(String numberOfTutGrp) {
        // check empty
        if (!numberOfTutGrp.isEmpty()) {
            // check number format
            try {
                int number = Converter.convertStringToInteger(numberOfTutGrp);

                // check negative value
                if (number > 0) {
                    return number;
                } else {
                    // smaller and equals to 0
                    Message.ErrorMessage("Number Of Tutorial Group Create cannot in NEGATIVE value ( eg : 5 )");
                }
            } catch (NumberFormatException ex) {
                Message.ErrorMessage("Invalid format error\nNumber Of Tutorial Group Create must in NUMERIC FROMAT! ( eg : 5 )");
            }
        } else {
            Message.ErrorMessage("Number Of Tutorial Group Create cannot be empty");
        }
        return -1;
    }
}
