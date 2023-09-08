package dao;

import adt.DoublyLinkedList;
import entity.Student;
import java.io.IOException;
import utility.FileUtility;

/**
 *
 * @author ENG JIA JEAN
 */
public class StudentDAO {

    private final String studFile = "student.txt";

    private FileUtility fu = new FileUtility();

    public void saveToFile(String str) throws IOException {
        fu.append(studFile, str);
    }

    public DoublyLinkedList<Student> retriveFromFile() throws IOException {
        adt.ArrayList<String[]> studRecords = new adt.ArrayList<>();
        adt.DoublyLinkedList<Student> stud = new adt.DoublyLinkedList<>();
        studRecords = fu.read(studFile);
        for (int i = 0; i < studRecords.size(); i++) {
            stud.add(new Student(studRecords.get(i)[0], studRecords.get(i)[1],
                    Integer.parseInt(studRecords.get(i)[2]),
                    Double.parseDouble(studRecords.get(i)[3]), studRecords.get(i)[4]));
        }
        return stud;
    }

    public void clear() throws IOException {
        fu.clear(studFile);
    }
    
    public boolean checkFileExist(){
        return fu.exist(studFile);
    }

}
