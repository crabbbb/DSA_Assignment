package dao;

/**
 * @author LOH XIN JIE
 */
public abstract class DBModel<T> implements Comparable<T> {

    private final String FILENAME;

    public DBModel() {
        FILENAME = "";
    }

    public DBModel(String filename) {
        this.FILENAME = filename;
    }

    public String getFileName() {
        return FILENAME;
    }

    /**
     * getPrimary is use to return the primary key <br>
     * primary key only can be String / int other will not be accepted <br>
     * it will cause contain function cannot be work and return null;
     */
    public abstract Object getPrimary();
}
