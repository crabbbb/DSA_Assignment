package dao;

/**
 * @author LOH XIN JIE
 */
import adt.*;
import java.io.*;
import java.util.Iterator;
import utility.Message;

/*in this class all exception will be throw to ui to handle, except IOException that closeFile() maybe hit*/
public class DbSet<T extends DBModel> implements DBInterface<T> {

    private T t;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private File file;
    private FileInputStream fis;
    private FileOutputStream fos;

    public DbSet(T newTable) {
        this.t = newTable;
    }

    /**
     * check the existing of the txt file, <br>
     * if no exist will create a new one
     *
     * @return true when file already been open else will be false
     * @throws IOException if the file cannot be create
     */
    private boolean openFile() throws IOException {
        file = new File(t.getFileName() + ".txt");

        //no exist
        if (!file.exists()) {
            //create a new file
            if (createFile()) {
                return true;
            }

        }

        return false;
    }

    /**
     * @return false when the file is already exist else will be true
     * @throws IOException when the file cannot be create
     */
    private boolean createFile() throws IOException {
        try {
            //File create message
            Message.InformationMessage("File create successfull");

            return new File(t.getFileName() + ".txt").createNewFile();
        } catch (IOException ex) {
            //error cause cannot open
            throw new IOException("File cannot be create : " + ex.getMessage());
        }
    }

    /**
     * close for <b>ObjectInputStream</b> <br>
     * if cannot close will prompt out error message at console
     *
     * <b>IOException</b> cause when the file cannot be close
     */
    private void closeFile(ObjectInputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ex) {
            //print error message
            System.out.println("Cloud not close " + t.getFileName() + ".txt : " + ex.getMessage());
        }
    }

    /**
     * close for <b>ObjectOutputStream</b> <br>
     * if cannot close will prompt out error message at console
     *
     * <b>IOException</b> cause when the file cannot be close
     */
    private void closeFile(ObjectOutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ex) {
            //print error message
            System.out.println("Cloud not close " + t.getFileName() + ".txt : " + ex.getMessage());
        }
    }

    private boolean checkFileEmpty() {
        return file.length() == 0;
    }

    /**
     * read out all data from file
     *
     * @throws IOException - when create & open file not successful
     * @throws ClassNotFoundException
     */
    private DoublyLinkedList<T> readFileData() throws IOException, ClassNotFoundException {
        try {
            openFile();
            if (!checkFileEmpty()) {
                // file is not empty == can read data
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);

                return (DoublyLinkedList<T>) ois.readObject();
            }

            return null;
        } finally {
            closeFile(ois);
        }
    }

    /**
     * Precondition : list cannot be Null write all data into file
     *
     * @throws IOException - when create & open file not successful
     * @throws ClassNotFoundException
     */
    private void writeFileData(DoublyLinkedList<T> alist) throws IOException, ClassNotFoundException {
        try {
            openFile();
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(alist);
            oos.flush();
        } finally {
            closeFile(oos);
        }
    }

    /**
     * get all data in corresponding table
     *
     * @return DoublyLinkedList<T> when successful get data from corresponding
     * file, if no data inside will return as null
     * @throws java.io.IOException when File cannot be open / cannot create a
     * new file at openFile() method
     * @throws java.lang.ClassNotFoundException when class not found
     *
     * when hit this exception please stop your process...
     */
    @Override
    public DoublyLinkedList<T> getAll() throws IOException, ClassNotFoundException {
        return readFileData();
    }

    /**
     * Add is a function that interact with file to adding a new value<br>
     * inside add function will check repeat ID the ID that already exist in
     * file cannot be save will return false
     *
     * @param newEntry cannot be null and <b>cannot be repeat</b> ( mean that
     * already exist in file, checking will base on ID )
     * @throws java.io.IOException when File cannot be open / cannot create a
     * new file
     * @throws java.lang.ClassNotFoundException when class not found
     * @return true when successful added , else will be false
     */
    @Override
    public boolean Add(T newEntry) throws IOException, ClassNotFoundException {
        if (newEntry != null) {
            // read data from file
            DoublyLinkedList<T> alist = readFileData();

            // if alist is null mean file is empty then create a new alist to makesure will not hit NullPointerException
            if (alist == null) {
                alist = new DoublyLinkedList<>();

                // add newEntry into alist
                alist.add(newEntry);

                // update file content
                writeFileData(alist);

                return true;
            } else {
                // not null check repeat and add
                if (alist.indexOf(newEntry) == -1) {
                    // add newEntry into alist
                    alist.add(newEntry);

                    // update file content
                    writeFileData(alist);

                    return true;
                }
            }

        }

        return false;
    }

    /**
     * delete base on ID, other data can no need to fill in but ID is required
     *
     * Precondition : in the T value must have primary key
     *
     * @return false when the entry is null; file not data; and the entry is not
     * found in file, true when process is successful
     * @param anEntry - cannot but null
     */
    @Override
    public boolean Delete(T anEntry) throws IOException, ClassNotFoundException {
        // check entry cannot be null
        if (anEntry != null) {
            // get data from file
            DoublyLinkedList<T> alist = readFileData();
            if (alist != null) {
                // file have data > check data exist in list or not
                T targetData = contains(anEntry.getPrimary(), alist);
                if (targetData != null) {
                    // data exist in list then process delete
                    alist.remove(targetData);
                    // update file data
                    writeFileData(alist);

                    return true;
                }
            }
        }

        return false; // when the entry is null; file not data; and the entry is not found in file
    }

    /**
     * Precondition : entry cannot be empty and null, id must have value update
     * must have full data for the class, id cannot change
     *
     * @return if the newEntry not found, is null and file is empty will return
     * false
     */
    @Override
    public boolean Update(T newEntry) throws IOException, ClassNotFoundException {
        // check newEntry cannot be null
        if (newEntry != null) {

            DoublyLinkedList<T> alist = readFileData();

            if (alist != null) {
                // have data exist then get index
                int index = indexOf(newEntry.getPrimary(), alist);

                if (index != -1) {
                    // data exist in list
                    alist.replace(++index, newEntry);

                    writeFileData(alist);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public T getWithId(Object ID) throws IOException, ClassNotFoundException {
        // read data from file
        DoublyLinkedList<T> alist = readFileData();

        if (alist == null) {
            // no data inside
            return null;
        }

        return contains(ID, alist);

    }

    /**
     * use to find the target data from alist
     */
    private T contains(Object primaryKey, DoublyLinkedList<T> alist) {
        Iterator<T> iterator = alist.iterator();

        while (iterator.hasNext()) {
            T data = iterator.next();
            if (primaryKey.equals(data.getPrimary())) {
                return data;
            }
        }
        return null;
    }

    /**
     * use to find the target data from alist
     */
    private int indexOf(Object primaryKey, DoublyLinkedList<T> alist) {
        Iterator<T> iterator = alist.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            T data = iterator.next();
            if (primaryKey.equals(data.getPrimary())) {
                return i;
            }

            i++;
        }

        // no found
        return -1;
    }

}
