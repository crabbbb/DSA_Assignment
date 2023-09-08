package dao;

import adt.ArrayList;
import java.io.IOException;
import utility.FileUtility;

/**
 *
 * @author ENG JIA JEAN
 */
public class HistoryDAO {

    private final String historyFile = "history.txt";

    private FileUtility fu = new FileUtility();

    public void saveToFile(String str) throws IOException {
        fu.append(historyFile, str);
    }

    public ArrayList<String[]> retriveFromFile() throws IOException {
        adt.ArrayList<String[]> history = new adt.ArrayList<>();

        history = fu.read(historyFile);

        return history;
    }

    public boolean checkFileExist() {
        return fu.exist(historyFile);
    }

}
