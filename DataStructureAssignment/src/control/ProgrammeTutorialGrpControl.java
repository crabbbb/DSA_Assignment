package control;

import adt.DoublyLinkedList;
import entity.*;
import dao.*;
import java.io.IOException;
import java.util.Iterator;
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

    public DoublyLinkedList<TutorialGroup> getTtlGrpByPrograme(String programmeId) {
        try {
            DoublyLinkedList<TutorialGroup> tlist = db.TutorialGroup.getAll();

            // if isEmpty and is null no need run
            if (tlist != null && !tlist.isEmpty()) {
                DoublyLinkedList<TutorialGroup> returnList = new DoublyLinkedList<>();

                Iterator<TutorialGroup> iterator = tlist.iterator();
                while (iterator.hasNext()) {
                    TutorialGroup t = iterator.next();
                    if (t.getProgrammeID().equals(programmeId)) {
                        returnList.add(t);
                    }
                }

                returnList.sort();

                return returnList;
            }

        } catch (IOException | ClassNotFoundException ex) {
            Message.ErrorMessage(ex.getMessage());
        }

        return null;
    }

    public void addTutorialGrpByGenerateID(int numberOfTutGrp, String programmeID) throws IOException, ClassNotFoundException {
        try {
            for (int i = 1; i <= numberOfTutGrp; i++) {
                db.TutorialGroup.Add(new TutorialGroup(programmeID + "G" + i, programmeID));
            }
        } catch (IOException | ClassNotFoundException ex) {
            Message.ErrorMessage(ex.getMessage());
        }
    }

    /**
     * Cause by programmeID : when the programmeID is null or empty generate
     * will unsuccessful Cause by tlist : when last tutorialGrpID not in correct
     * format generate will unsuccessful
     */
    public String getNextTutorialID(String programmeID, DoublyLinkedList<TutorialGroup> tlist) {
        if (programmeID != null && !programmeID.isEmpty()) {
            if (tlist != null && tlist.size() > 0) {
                try {
                    // get last tutorialGrp ID
                    String previousID = tlist.get(tlist.size()).getTutorialGrpId();

                    // remove unneccessary value
                    previousID = previousID.replaceAll(programmeID + "G", "");

                    int grpNo = Integer.parseInt(previousID);

                    return programmeID + "G" + (++grpNo);
                } catch (NumberFormatException ex) {
                    // invalid value occurs
                    return null;
                }

            } else {
                return programmeID + "G1";
            }
        }

        return null;
    }

    public int getTotalNoOfStudentByProgramme(DoublyLinkedList<TutorialGroup> tlist) {
        if (tlist != null && !tlist.isEmpty()) {
            int ttlNo = 0;

            // have data
            Iterator<TutorialGroup> iterator = tlist.iterator();

            while (iterator.hasNext()) {
                ttlNo += iterator.next().getGroupSize();
            }

            return ttlNo;
        } else {
            // no data
            return 0;
        }
    }

    /**
     * @return null when id no exist in list and tlist is empty
     */
    public TutorialGroup searchTutorialGrpByID(String targetID, DoublyLinkedList<TutorialGroup> tlist) {
        // make sure is not empty
        if (tlist != null && !tlist.isEmpty()) {
            // do sorting
            tlist.sort();

            TutorialGroup currentTutorialGroup = null;

            for (int i = 0; i < tlist.size(); i++) {
                currentTutorialGroup = tlist.get(i + 1);

                int result = currentTutorialGroup.compareTo(new TutorialGroup(targetID));

                if (result > 0) {
                    // already bigger than
                    return null;
                } else if (result == 0) {
                    return currentTutorialGroup;
                }
            }
        }

        return null;
    }

    /**
     * @return null when there is no unsuccessful delete occurs, else will
     * return the list
     */
    public DoublyLinkedList<TutorialGroup> deleteListOfTutorialGrp(DoublyLinkedList<TutorialGroup> tlist) {
        DoublyLinkedList<TutorialGroup> unsuccessList = new DoublyLinkedList<>();
        Iterator<TutorialGroup> iterator = tlist.iterator();

        while (iterator.hasNext()) {
            TutorialGroup t = iterator.next();
            if (!deleteTutorialGrp(t.getTutorialGrpId())) {
                unsuccessList.add(t);
            }
        }

        return unsuccessList.size() <= 0 ? null : unsuccessList;
    }

    /**
     * @return null when there is no unsuccessful add occurs, else will return
     * the list
     */
    public DoublyLinkedList<TutorialGroup> addListOfTutorialGrp(DoublyLinkedList<TutorialGroup> tlist) {
        DoublyLinkedList<TutorialGroup> unsuccessList = new DoublyLinkedList<>();
        Iterator<TutorialGroup> iterator = tlist.iterator();

        while (iterator.hasNext()) {
            TutorialGroup t = iterator.next();
            if (!addTutorialGrp(t)) {
                unsuccessList.add(t);
            }
        }

        return unsuccessList.size() <= 0 ? null : unsuccessList;
    }

    public boolean addTutorialGrp(TutorialGroup ttlGrp) {
        try {
            return db.TutorialGroup.Add(ttlGrp);
        } catch (IOException | ClassNotFoundException ex) {
            Message.ErrorMessage(ex.getMessage());
        }

        return false;
    }

}
