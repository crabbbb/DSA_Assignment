package adt;

import dao.DBModel;
import test.*;
import java.io.IOException;

/**
 * @author LOH XIN JIE
 */
public interface DBInterface<T extends DBModel> {

    boolean Add(T newEntry) throws IOException, ClassNotFoundException;

    boolean Delete(T anEntry);

    boolean Update(T newEntry);

    T getWithId(int ID) throws IOException, ClassNotFoundException;

    T getWithId(String ID);

    test.ArrayList<T> getAll() throws IOException, ClassNotFoundException;
}
