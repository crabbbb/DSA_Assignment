package dao;

/**
 * @author LOH XIN JIE
 */
import adt.*;
import java.io.*;

public class DbSet<T extends DBModel> {

    private T t;

    public DbSet(T t) {
        this.t = t;
    }

    /**
     * check the existing of the txt file, <br>
     * if no exist will create a new one
     *
     * @return File when the file is already be open
     * @throws IOException if the file cannot be create
     */
    private File openFile() throws IOException {
        File openFile = new File(t.getFileName() + ".txt");

        //no exist
        if (!openFile.exists()) {
            //create a new file
            createFile();
        }

        //already open
        return openFile;
    }

    /**
     * @return false when the file is already exist else will be true
     * @throws IOException when the file cannot be create
     */
    private boolean createFile() throws IOException {
        try {
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
     * IOException cause when the file cannot be close
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
     * IOException cause when the file cannot be close
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

    /**
     * get all data in corresponding table
     *
     * @return ArrayList<T> when successful get data from corresponding file
     * @throws IOException when File cannot be open / cannot create a new file
     * at openFile() method
     * @throws ClassNotFoundException when class not found
     *
     * when hit this exception please stop your process...
     */
    public ArrayList<T> getData() throws IOException, ClassNotFoundException {

        ObjectInputStream input = null;

        try {
            input = new ObjectInputStream(new FileInputStream(openFile()));

            ArrayList<T> tlist = (ArrayList<T>) input.readObject();

            return tlist;

        } finally {
            //will be close when have error
            closeFile(input);
        }
    }

    //Insert
    public boolean Add(T t) throws IOException, ClassNotFoundException {
        ObjectOutputStream output = null;

        //open the file and save data inside
        try {
            output = new ObjectOutputStream(new FileOutputStream(openFile()));

            output.writeObject(t);

            return true;
        } finally {
            closeFile(output);
        }
    }

}
