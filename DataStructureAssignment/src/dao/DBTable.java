package dao;

/**
 * @author LOH XIN JIE
 */
import entity.*;

public class DBTable {

    public DbSet<Programme> Programme;
    public DbSet<Student> Student;
    public DbSet<TutorialGroup> TutorialGroup;
    public DbSet<Course> Course;
    public DbSet<CourseProgramme> CourseProgramme;

    public DBTable() {
        this.Programme = new DbSet<>(new Programme());
        this.Student = new DbSet<>(new Student());
        this.TutorialGroup = new DbSet<>(new TutorialGroup());
        this.Course = new DbSet<>(new Course());
        this.CourseProgramme = new DbSet<>(new CourseProgramme());
    }
}
