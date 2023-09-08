package control;

import adt.DoublyLinkedList;
import dao.TutGrpDAO;
import entity.TutorialGroup;
import java.io.IOException;

/**
 *
 * @author ENG JIA JEAN
 */
public class TutControl {

    private TutGrpDAO tutDao = new TutGrpDAO();

    public DoublyLinkedList<TutorialGroup> getTutorialGrp() throws IOException {
        DoublyLinkedList<TutorialGroup> tut = tutDao.retriveFromFile();
        return tut;
    }

    public void modifyGrp(DoublyLinkedList<TutorialGroup> tutGrp, String oldTutGrp, String newTutGrp) throws IOException {
        String tut = "";
        for (int i = 0; i < tutGrp.size(); i++) {
            if (tutGrp.get(i + 1).getTutorialGrpId().equals(oldTutGrp)) {
                tutGrp.get(i + 1).setGroupSize(tutGrp.get(i + 1).getGroupSize() - 1);
            } else if (tutGrp.get(i + 1).getTutorialGrpId().equals(newTutGrp)) {
                tutGrp.get(i + 1).setGroupSize(tutGrp.get(i + 1).getGroupSize() + 1);
            }
            tut += tutGrp.get(i + 1).getTutorialGrpId()
                    + "," + tutGrp.get(i + 1).getGroupSize()
                    + "," + tutGrp.get(i + 1).getProgrammeID() + "\n";
        }
        tutDao.clear();
        tutDao.saveToFile(tut);
    }

    public void addGrpSize(String tempTut) throws IOException {
        DoublyLinkedList<TutorialGroup> tutGrp = getTutorialGrp();
        String tut = "";
        for (int i = 0; i < tutGrp.size(); i++) {
            if (tutGrp.get(i + 1).getTutorialGrpId().equals(tempTut)) {
                tutGrp.get(i + 1).setGroupSize(tutGrp.get(i + 1).getGroupSize() + 1);
            }
            tut += tutGrp.get(i + 1).getTutorialGrpId()
                    + "," + tutGrp.get(i + 1).getGroupSize()
                    + "," + tutGrp.get(i + 1).getProgrammeID() + "\n";
        }
        tutDao.clear();
        tutDao.saveToFile(tut);
    }

    public void subtractGrpSize(String tempTut) throws IOException {
        DoublyLinkedList<TutorialGroup> tutGrp = getTutorialGrp();
        String tut = "";
        for (int i = 0; i < tutGrp.size(); i++) {
            if (tutGrp.get(i + 1).getTutorialGrpId().equals(tempTut)) {
                tutGrp.get(i + 1).setGroupSize(tutGrp.get(i + 1).getGroupSize() - 1);
            }
            tut += tutGrp.get(i + 1).getTutorialGrpId()
                    + "," + tutGrp.get(i + 1).getGroupSize()
                    + "," + tutGrp.get(i + 1).getProgrammeID() + "\n";
        }
        tutDao.clear();
        tutDao.saveToFile(tut);
    }

}
