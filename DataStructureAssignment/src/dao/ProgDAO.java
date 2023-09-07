package dao;

import adt.DoublyLinkedList;
import entity.Programme;
import entity.TutorialGroup;
import java.io.IOException;
import utility.FileUtility;

/**
 *
 * @author ENG JIA JEAN
 */
public class ProgDAO {

    private final String progFile = "prog.txt";
    private FileUtility fu = new FileUtility();

    public void saveToFile(String str) throws IOException {
        fu.append(progFile, str);
    }

    public DoublyLinkedList<Programme> retriveFromFile() throws IOException {
        adt.ArrayList<String[]> progRecords = new adt.ArrayList<>();
        adt.DoublyLinkedList<Programme> prog = new adt.DoublyLinkedList<>();
        progRecords = fu.read(progFile);
        for (int i = 0; i < progRecords.size(); i++) {
            prog.add(new Programme(progRecords.get(i)[0], progRecords.get(i)[1],
                    Integer.parseInt(progRecords.get(i)[3]), progRecords.get(i)[2]));
        }
        return prog;
    }

    public void clear() throws IOException {
        fu.clear(progFile);
    }

    public boolean checkFileExist() {
        return fu.exist(progFile);
    }

}
