package dao;

/**
 * @author LOH XIN JIE
 */
public class DBModel {

    private final String FILENAME;

    public DBModel(String filename) {
        this.FILENAME = filename;
    }

    public String getFileName() {
        return FILENAME;
    }
}
