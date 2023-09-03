package dao;

/**
 * @author LOH XIN JIE
 */
import entity.*;

public class DBTable {

    public DbSet<Programme> Programme;
    public DbSet<Student> Student;
    public DbSet<TutorialGroup> TutorialGroup;

    public DBTable() {
        this.Programme = new DbSet<>(new Programme());
        this.Student = new DbSet<>(new Student());
        this.TutorialGroup = new DbSet<>(new TutorialGroup());
    }
}
