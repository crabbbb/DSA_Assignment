package dao;

/**
 * @author LOH XIN JIE
 */
//import adt.*;
import adt.DBInterface;
import java.io.*;
import test.*;
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
    private ArrayList<T> readFileData() throws IOException, ClassNotFoundException {
        try {
            openFile();
            if (!checkFileEmpty()) {
                // file is not empty == can read data
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);

                return (ArrayList<T>) ois.readObject();
            }

            return null;
        } finally {
            closeFile(ois);
        }
    }

    /**
     * Precondition : alist cannot be Null write all data into file
     *
     * @throws IOException - when create & open file not successful
     * @throws ClassNotFoundException
     */
    private void writeFileData(ArrayList<T> alist) throws IOException, ClassNotFoundException {
        try {
            openFile();
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(alist);
        } finally {
            closeFile(oos);
        }
    }

    /**
     * get all data in corresponding table
     *
     * @return ArrayList<T> when successful get data from corresponding file, if
     * no data inside will return as null
     * @throws java.io.IOException when File cannot be open / cannot create a
     * new file at openFile() method
     * @throws java.lang.ClassNotFoundException when class not found
     *
     * when hit this exception please stop your process...
     */
    @Override
    public ArrayList<T> getAll() throws IOException, ClassNotFoundException {
        ArrayList<T> alist = readFileData();
        return alist;
    }

    /**
     * Add is a function that interact with file to adding a new value
     *
     * newEntry cannot be null if null will return false
     *
     * @param newEntry - the only value that you want to added
     * @throws java.io.IOException when File cannot be open / cannot create a
     * new file
     * @throws java.lang.ClassNotFoundException when class not found
     * @return true when successful added , else will be false
     */
    @Override
    public boolean Add(T newEntry) throws IOException, ClassNotFoundException {
        if (newEntry != null) {
            // read data from file
            ArrayList<T> alist = readFileData();

            // if alist is null mean file is empty then create a new alist
            if (alist == null) {
                alist = new ArrayList<>();
            }

            // add newEntry into alist
            alist.add(newEntry);

            // update file content
            writeFileData(alist);

            return true;
        }

        return false;
    }

    // method under here haven finish implements
    @Override
    public boolean Delete(T anEntry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Update(T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T getWithId(int ID) throws IOException, ClassNotFoundException {
        // read data from file
        ArrayList<T> alist = readFileData();

        if (alist == null) {
            // no data inside
            return null;
        }

        // update file content
        writeFileData(alist);
        return null;
    }

    @Override
    public T getWithId(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private T contains(int primaryKey, ArrayList<T> alist) {
        for (int i = 0; i < alist.getNumberOfEntries(); i++) {
//            if () {
//                t.getPrimary();
//            }
        }
        return null;
    }

}
