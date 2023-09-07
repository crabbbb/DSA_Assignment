package dao;

import adt.*;
import entity.TutorialGroup;
import java.io.IOException;
import utility.FileUtility;

/**
 *
 * @author ENG JIA JEAN
 */
public class TutGrpDAO {

    private final String tutFile = "tutGrp.txt";
    private FileUtility fu = new FileUtility();

    public void saveToFile(String str) throws IOException {
        fu.append(tutFile, str);
    }

    public DoublyLinkedList<TutorialGroup> retriveFromFile() throws IOException {
        adt.ArrayList<String[]> tutGrpRecords = new adt.ArrayList<>();
        adt.DoublyLinkedList<TutorialGroup> tutGrp = new adt.DoublyLinkedList<>();
        tutGrpRecords = fu.read(tutFile);
        for (int i = 0; i < tutGrpRecords.size(); i++) {
            tutGrp.add(new TutorialGroup(tutGrpRecords.get(i)[0], Integer.parseInt(tutGrpRecords.get(i)[1]),
                    tutGrpRecords.get(i)[2]));
        }
        return tutGrp;
    }

    public void clear() throws IOException {
        fu.clear(tutFile);
    }

    public boolean checkFileExist() {
        return fu.exist(tutFile);
    }

}
