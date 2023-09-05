package control;

import entity.*;
import dao.*;
import java.io.IOException;
import utility.Message;

/**
 * @author LOH XIN JIE
 */
public class ProgrammeTutorialGrpControl {

    private DBTable db;

    public ProgrammeTutorialGrpControl() {
        this.db = new DBTable();
    }

    public boolean deleteTutorialGrp(String tutorialGrpId) {
        try {
            // check null && id empty
            if (tutorialGrpId != null && !tutorialGrpId.isEmpty()) {
                return db.TutorialGroup.Delete(new TutorialGroup(tutorialGrpId));
            }
        } catch (IOException | ClassNotFoundException ex) {
            // not successful, display out the exception
            Message.ErrorMessage(ex.getMessage());
        }

        return false;
    }

    public void addTutorialGrp(int numberOfTutGrp, String programmeID) throws IOException, ClassNotFoundException {
        for (int i = 1; i <= numberOfTutGrp; i++) {
            db.TutorialGroup.Add(new TutorialGroup(programmeID + "G" + i, 0, programmeID));
        }
    }
}
