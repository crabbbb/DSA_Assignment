package control;

import adt.DoublyLinkedList;
import dao.ProgDAO;
import entity.Programme;
import java.io.IOException;

/**
 *
 * @author ENG JIA JEAN
 */
public class ProgControl {

    private ProgDAO progDao = new ProgDAO();

    public DoublyLinkedList<Programme> getProg() throws IOException {
        DoublyLinkedList<Programme> prog = progDao.retriveFromFile();
        return prog;
    }
}
