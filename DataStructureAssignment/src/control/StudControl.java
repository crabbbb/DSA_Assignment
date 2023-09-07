package control;

import adt.ArrayList;
import adt.DoublyLinkedList;
import dao.StudTutProgInitializer;
import dao.StudentDAO;
import dao.HistoryDAO;
import entity.Student;
import java.io.IOException;

/**
 *
 * @author ENG JIA JEAN
 */
public class StudControl {

    private StudentDAO studDao = new StudentDAO();
    private HistoryDAO historyDao = new HistoryDAO();

    private StudTutProgInitializer stpi = new StudTutProgInitializer();

    public DoublyLinkedList<Student> getStudents() throws IOException {
        DoublyLinkedList<Student> stud = studDao.retriveFromFile();
        return stud;
    }

    public int checkStudId(String id) throws IOException {
        DoublyLinkedList<Student> stud = studDao.retriveFromFile();
        //1 - id format wrong
        //2 - id exists
        //3 - length problem
        //4 - correct
        if (id.length() == 10) {
            if (!Character.isLetter(id.charAt(2)) || !Character.isLetter(id.charAt(3))
                    || !Character.isLetter(id.charAt(4))) {
                return 1;
            }
            for (int i = 0; i < stud.size(); i++) {
                if (stud.get(i + 1).getId().equals(id)) {
                    return 2;
                }
            }
        } else {
            return 3;
        }
        return 4;
    }

    public void addStud(String student, String actionPerformed) throws IOException {
        studDao.saveToFile(student);
        historyDao.saveToFile(actionPerformed);
    }

    public void delStud(DoublyLinkedList<Student> stud, String actionPerformed) throws IOException {
        String student = "";
        for (int i = 0; i < stud.size(); i++) {
            student += stud.get(i + 1).getId() + "," + stud.get(i + 1).getName()
                    + "," + stud.get(i + 1).getAge() + ","
                    + stud.get(i + 1).getCgpa()
                    + "," + stud.get(i + 1).getTutGrpId() + "\n";
        }
        studDao.clear();
        studDao.saveToFile(student);
        historyDao.saveToFile(actionPerformed);
    }

    public void changeStud(String student, String actionPerformed) throws IOException {
        studDao.clear();
        studDao.saveToFile(student);
        historyDao.saveToFile(actionPerformed);
    }

    public void displayStud(String actionPerformed) throws IOException {
        historyDao.saveToFile(actionPerformed);
    }

    public ArrayList<Student> searchStudName(String keyword, String actionPerformed) throws IOException {
        DoublyLinkedList<Student> stud = studDao.retriveFromFile();
        ArrayList<Student> tempStud = new adt.ArrayList<>();
        for (int i = 0; i < stud.size(); i++) {
            if (stud.get(i + 1).getName().contains(keyword)) {
                tempStud.add(stud.get(i + 1));
            }
        }
        historyDao.saveToFile(actionPerformed);
        return tempStud;
    }

    public ArrayList<Student> searchStudId(String keyword, String actionPerformed) throws IOException {
        DoublyLinkedList<Student> stud = studDao.retriveFromFile();
        ArrayList<Student> tempStud = new adt.ArrayList<>();
        for (int i = 0; i < stud.size(); i++) {
            if (stud.get(i + 1).getId().contains(keyword)) {
                tempStud.add(stud.get(i + 1));
            }
        }
        historyDao.saveToFile(actionPerformed);
        return tempStud;
    }

    public void filterGrp(String actionPerformed) throws IOException {
        historyDao.saveToFile(actionPerformed);
    }

    public ArrayList<String[]> generateHistory() throws IOException {
        ArrayList<String[]> history = new adt.ArrayList<>();
        history = historyDao.retriveFromFile();
        return history;
    }
}
