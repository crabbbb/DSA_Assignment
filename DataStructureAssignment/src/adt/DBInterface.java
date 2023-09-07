package adt;

import dao.DBModel;
import java.io.IOException;

/**
 * @author LOH XIN JIE
 */
public interface DBInterface<T extends DBModel> {

    boolean Add(T newEntry) throws IOException, ClassNotFoundException;

    boolean Delete(T anEntry) throws IOException, ClassNotFoundException;

    boolean Update(T newEntry) throws IOException, ClassNotFoundException;

    T getWithId(Object ID) throws IOException, ClassNotFoundException;

    DoublyLinkedList<T> getAll() throws IOException, ClassNotFoundException;
}